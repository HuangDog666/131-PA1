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
			while (true) {
				System.out.print(Message.NEWCOMMAND);
				String command = scan.nextLine();
				if (command.startsWith("exit")) {
					System.out.print(Message.GOODBYE);
					break;
				}
				
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
			e.printStackTrace();
		}finally {
			scan.close();
		}
		
	}

}
