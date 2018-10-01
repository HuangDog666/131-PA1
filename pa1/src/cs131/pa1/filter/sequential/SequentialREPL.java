package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class SequentialREPL {

	static String currentWorkingDirectory;
	public static boolean output = false;
	
	public static void main(String[] args){
		currentWorkingDirectory = new File("").getAbsolutePath();
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print(Message.WELCOME);
			//use while loop to let users enter their commands, unless the command is exit
			while (true) {
				System.out.print(Message.NEWCOMMAND);
				String command = scan.nextLine();
				if (command.startsWith("exit")) {
					System.out.print(Message.GOODBYE);
					break;
				}
				//use sequentialCommandBuilder to manage the commands
				List<SequentialFilter> filters = SequentialCommandBuilder.createFiltersFromCommand(command);
				if (filters == null) {
					continue;
				}
				for (SequentialFilter filter:filters) {
					filter.process();
				}
//				if (output) {
//					System.out.println();
//					output = false;
//				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();//print this throwable and its backtrace to the standard error stream
		}finally {
			scan.close();
		}
		
	}

}
