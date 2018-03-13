package com.kkch.xxworld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Count {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\CH\\git\\NIFG tagSNP Selection Program开发文件\\src";
		ArrayList<File> files = getFiles(path,".java");
		int tln = 0;
		int tcnum = 0;
		for(File file:files) {
			int ln = 0;
			int cnum = 0;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String l = null;
			while((l=reader.readLine())!=null) {
				ln++;
				cnum += l.trim().length();
			}
			tcnum += cnum;
			tln += ln;
			reader.close();
			System.out.println(file+" >> "+ln+"行"+cnum+"字");
		}
		System.out.println("共"+tln+"行"+tcnum+"字");
	}

	private static ArrayList<File> getFiles(String path, String string) {
		ArrayList<File> fs = new ArrayList<>();
		File file = new File(path);
		if(file.isDirectory()) {
			for(File p:file.listFiles()) {
				fs.addAll(getFiles(p.getAbsolutePath(),string));
			}
		}else {
			if(path.endsWith(string)) {
				fs.add(file);
			}
		}
		return fs;
	}
	
}
