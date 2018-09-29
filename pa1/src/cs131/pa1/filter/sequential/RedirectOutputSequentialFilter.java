package cs131.pa1.filter.sequential;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import cs131.pa1.filter.Message;

public class RedirectOutputSequentialFilter extends SequentialFilter {

	private File file;
	private FileOutputStream fos;
	private OutputStreamWriter osw;
	private BufferedWriter bw;

	public RedirectOutputSequentialFilter(String command) {

		String[] commands = command.split(" ");
		if (commands.length < 2) {
			output.add(String.format(Message.REQUIRES_INPUT.toString(), ">"));
			input.clear();
		}
		String filePath = commands[1];
		if (!filePath.startsWith(FILE_SEPARATOR)) {
			filePath = new File(SequentialREPL.currentWorkingDirectory, filePath).getAbsolutePath();
		}
		file = new File(filePath);
		try {
			if (!file.exists()) {

				file.createNewFile();

			}
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String processLine(String line) {
		try {
			bw.write(line);
			bw.write("\n");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (isDone()) {
			try {
				fos.close();
				osw.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
