package cs131.pa1.filter.sequential;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import cs131.pa1.filter.Message;

public class CatSequentialFilter extends SequentialFilter {
	
	private boolean error;
	
	

	public boolean isError() {
		return error;
	}

	public CatSequentialFilter(String commad) {

		input = new LinkedList<String>();
		output = new LinkedList<String>();

		String[] commands = commad.split(" ");
		if (commands.length < 2) {
			output.add(String.format(Message.FILE_NOT_FOUND.toString(), "cat"));
			return;
		}
		if (!new File(SequentialREPL.currentWorkingDirectory + FILE_SEPARATOR + commands[1].trim()).exists()) {
			
			error = true;
			return;
		}
		/*output one or more files*/
		String fileName = "";
		for (int i = 1; i < commands.length; i++) {

			if (commands[i].startsWith(FILE_SEPARATOR)) {
				fileName = commands[i];
			} else {
				if (!SequentialREPL.currentWorkingDirectory.endsWith(FILE_SEPARATOR)) {
					SequentialREPL.currentWorkingDirectory += FILE_SEPARATOR;
				}
				fileName = SequentialREPL.currentWorkingDirectory + commands[i];
			}
			FileReader fr = null;
			try {
				fr = new FileReader(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			BufferedReader bf = new BufferedReader(fr);
			String line;
			try {
				while ((line = bf.readLine()) != null) {
					input.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					fr.close();
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}

	}

	@Override
	protected String processLine(String line) {
		return line;
	}

}
