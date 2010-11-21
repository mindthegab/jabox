package org.jabox.scm.git;

public class Worker extends Thread {
	protected final Process process;
	protected Integer exit;

	protected Worker(Process process) {
		this.process = process;
	}

	public void run() {
		try {
			exit = process.waitFor();
		} catch (InterruptedException ignore) {
			return;
		}
	}
}
