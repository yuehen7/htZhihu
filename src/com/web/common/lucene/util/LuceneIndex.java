package com.web.common.lucene.util;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Paths;

import org.ansj.lucene6.AnsjAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class LuceneIndex implements AutoCloseable, Closeable {

    // 索引器
    public IndexWriter writer = null;

    protected String path;
    
    public LuceneIndex(String indexStorePath, OpenMode mode) throws IOException {
        // 索引文件的保存位置
        Directory dir = FSDirectory.open(Paths.get(indexStorePath));
        // 分析器
        Analyzer analyzer = analyzer();
        // 配置类
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(mode);// 创建模式 OpenMode.CREATE_OR_APPEND 添加模式
        writer = new IndexWriter(dir, iwc);
        path = indexStorePath;
    }
	
    public IndexReader reader() throws IOException {
        return DirectoryReader.open(writer, false, false);
    }

    public Analyzer analyzer() {
        return new AnsjAnalyzer();
    }
    
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		try {
	        IndexWriter writer = this.writer;
	        if (writer != null) {
	            writer.commit();
	            writer.close();
	            this.writer = null;
	        }
		} catch (IOException e) {
			throw e;
		}
	}

}
