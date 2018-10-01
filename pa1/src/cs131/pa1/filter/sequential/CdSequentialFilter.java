package cs131.pa1.filter.sequential;

import java.io.File;

import cs131.pa1.filter.Message;

public class CdSequentialFilter extends SequentialFilter {

	private String command;

	public CdSequentialFilter(String command) {
		this.command = command;
	}

	@Override
	protected String processLine(String line) {

		return null;
	}

	@Override
	public void process() {
		String cacheCurrent = SequentialREPL.currentWorkingDirectory;
		String[] commands = this.command.split(" ");
		if (commands.length < 2) {
			System.out.print(String.format(Message.INVALID_PARAMETER.name(), "cd"));
			return;
		}
	/*deal with the situation of ".."*/
		if (commands[1].startsWith("..")) {
			String[] dirs = SequentialREPL.currentWorkingDirectory.split(FILE_SEPARATOR);
			String newPath = "";
			for (int i = 0; i < dirs.length - 1; i++) {
				if (dirs[i]== null || dirs[i].length() ==0 ) {
					continue;
				}
				newPath += FILE_SEPARATOR;
				newPath += dirs[i];
			}
			if (!new File(newPath).exists()) {
				System.out.print(String.format(Message.DIRECTORY_NOT_FOUND.toString(), commands[1]));
			}
			SequentialREPL.currentWorkingDirectory = newPath;
			return;
		}
		if (commands[1].startsWith(".")) {
			return;
		}
		if (commands[1].startsWith(FILE_SEPARATOR)) {
			SequentialREPL.currentWorkingDirectory = commands[1];
			return;
		}
		
		if (SequentialREPL.currentWorkingDirectory.endsWith(FILE_SEPARATOR)) {
			SequentialREPL.currentWorkingDirectory += commands[1];
			return;
		}else {
			cacheCurrent += FILE_SEPARATOR;
			cacheCurrent += commands[1];
			
		}
		if (!new File(cacheCurrent).exists()) {
			System.out.print(String.format(Message.DIRECTORY_NOT_FOUND.toString(), command));
			return;
		}
		SequentialREPL.currentWorkingDirectory = cacheCurrent;
	}

}
