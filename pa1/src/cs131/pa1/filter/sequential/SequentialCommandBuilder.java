package cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import cs131.pa1.filter.Message;

public class SequentialCommandBuilder {

	public static List<SequentialFilter> createFiltersFromCommand(String command) {
		List<SequentialFilter> filters = new ArrayList<SequentialFilter>();
		String[] subCommands = command.split(("\\|"));//use split to separate commands
		SequentialFilter last = null;
		String lastCmd = null;
		for (String subC : subCommands) {
			
			while (subC.trim().indexOf(">") > 0) {
				SequentialFilter filter = constructFilterFromSubCommand(subC.substring(0, subC.indexOf(">")).trim());
				if (filter == null) {
					System.out.print(String.format(Message.COMMAND_NOT_FOUND.toString(), subC.trim()));
					return null;
				}
				if (last != null) {
					last.setNextFilter(filter);
				}
				last = filter;
				filters.add(filter);
				subC = subC.substring(subC.indexOf(">"));
			}
			//use several if statements to differentiate different commands
			if (subC.trim().equals("cd") || subC.trim().equals("cat") || subC.trim().equals(">")
					|| subC.trim().equals("grep")) {
				System.out.print(String.format(Message.REQUIRES_PARAMETER.toString(), subC.trim()));
				return null;
			}
			SequentialFilter filter = constructFilterFromSubCommand(subC.trim());
			if (filter == null) {
				System.out.print(String.format(Message.COMMAND_NOT_FOUND.toString(), subC.trim()));
				return null;
			}
			if (filter instanceof CatSequentialFilter && ((CatSequentialFilter) filter).isError()) {
				System.out.print(String.format(Message.FILE_NOT_FOUND.toString(), subC.trim()));
				return null;
			}
			if ((filter instanceof LsSequentialFilter || filter instanceof CdSequentialFilter
					|| filter instanceof CatSequentialFilter

					|| filter instanceof PwdSequentialFilter) && last != null) {
				System.out.print(String.format(Message.CANNOT_HAVE_INPUT.toString(), subC.trim()));
				return null;
			}

			if ((filter instanceof GrepSequentialFilter
//					|| filter instanceof CdSequentialFilter
					|| filter instanceof RedirectOutputSequentialFilter || filter instanceof WcSequentialFilter)
					&& last == null) {
				System.out.print(String.format(Message.REQUIRES_INPUT.toString(), subC.trim()));
				return null;
			}
			if ((last instanceof CdSequentialFilter || last instanceof RedirectOutputSequentialFilter)
					&& filter != null) {
				System.out.print(String.format(Message.CANNOT_HAVE_OUTPUT.toString(), lastCmd));
				return null;
			}
			if (last != null) {
				last.setNextFilter(filter);
			}
			last = filter;
			lastCmd = subC.trim();
			filters.add(filter);
		}
		SequentialFilter filter = constructFilterFromSubCommand("output ");
		last.setNextFilter(filter);

		filters.add(filter);
		return filters;
	}

	private static SequentialFilter determineFinalFilter(String command) {
		return null;
	}

	private static String adjustCommandToRemoveFinalFilter(String command) {
		return null;
	}

	//connect with those specific command classes
	private static SequentialFilter constructFilterFromSubCommand(String command) {
		if ((command.startsWith("ls ")) || command.trim().equals("ls")) {
			return new LsSequentialFilter();
		}
		if (command.startsWith("cd ")) {
			SequentialFilter filter = new CdSequentialFilter(command);
			return filter;
		}
		if (command.startsWith("pwd")) {
			return new PwdSequentialFilter();
		}
		if (command.startsWith("cat ")) {
			CatSequentialFilter filter = new CatSequentialFilter(command);
			return filter;
		}
		if (command.startsWith("grep ")) {
			return new GrepSequentialFilter(command);
		}
		if (command.startsWith("> ")) {
			return new RedirectOutputSequentialFilter(command);
		}
		if (command.startsWith("wc")) {
			return new WcSequentialFilter();
		}
		if (command.startsWith("uniq")) {
			return new UniqSequentialFilter();
		}
		if (command.startsWith("output ")) {
			return new OutputSequentialFilter();
		}

		return null;
	}

	private static boolean linkFilters(List<SequentialFilter> filters) {
		return false;
	}

}
