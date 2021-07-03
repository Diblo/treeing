/**
 *  Treeing. Crawling, indexing and searching web content
 *  Copyright (C) 2011 Kamran
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Contact Info:
 *  xeus.man@gmail.com
 */
package org.xeustechnologies.treeing;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

import org.apache.lucene.analysis.miscellaneous.LimitTokenCountAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.xeustechnologies.esl4j.LogManager;
import org.xeustechnologies.esl4j.Logger;

/**
 * @author Kamran
 * 
 */
public class HttpIndexer {

    private static final String MIME = "mime";
    private static final String URL = "url";
    private static final String CONTENTS = "contents";
    private static final int MAX_TOKEN_COUNT = 10_000;
    private final IndexWriter writer;
    private Logger logger = LogManager.getLogger( HttpIndexer.class );

    public HttpIndexer(String indexDir) throws IOException {
        FSDirectory dir = FSDirectory.open( Paths.get( indexDir ) );
        writer = new IndexWriter( dir, new IndexWriterConfig(
                new LimitTokenCountAnalyzer( new StandardAnalyzer(), MAX_TOKEN_COUNT ) ) );
    }

    public void indexUrl(String url, String content, String mime) {
        try {
            Document doc = new Document();

            doc.add( new TextField( CONTENTS, new StringReader( content ) ) );
            doc.add( new StoredField( URL, url ) );
            doc.add( new StoredField( MIME, mime ) );

            writer.addDocument( doc );
            logger.info( "Added: " + url );
            writer.commit();
        } catch (Exception e) {
            logger.info( "Could not add: " + url );
        }
    }

    public void commitIndex() throws CorruptIndexException, IOException {
        writer.commit();
    }

    public void closeIndex() throws IOException {
        writer.close();
    }
}
