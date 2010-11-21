package org.jabox.scm.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

/**
 * Executor is a wrapper for system executions.
 * 
 * @author dimitris
 * 
 */
public class Executor {

	/**
	 * Execution wrapper.
	 * 
	 * @param command
	 * @param envp
	 * @param dir
	 */
	public static void exec(String command, String[] envp, File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] Executing: "
				+ command);

		try {
			Process p = Runtime.getRuntime().exec(command, envp, dir);
			InputStream in = p.getInputStream();

			InputStream err = p.getErrorStream();
			BufferedReader bIn = new BufferedReader(new InputStreamReader(in));
			BufferedReader bErr = new BufferedReader(new InputStreamReader(err));
			String line;
			while ((line = bErr.readLine()) != null) {
				System.out.println(line);
			}

			while ((line = bIn.readLine()) != null) {
				System.out.println(line);
			}
			p.waitFor(); /* wait for subprocess to terminate */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int exec(final String commandLine,
			final String[] envp, final File dir, final boolean printOutput,
			final boolean printError, final long timeout) throws IOException,
			InterruptedException, TimeoutException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(commandLine, envp, dir);
		/* Set up process I/O. */
		Worker worker = new Worker(process);
		worker.start();
		try {
			worker.join(timeout);
			if (worker.exit != null)
				return worker.exit;
			else
				throw new TimeoutException();
		} catch (InterruptedException ex) {
			worker.interrupt();
			Thread.currentThread().interrupt();
			throw ex;
		} finally {
			process.destroy();
		}
	}

}
