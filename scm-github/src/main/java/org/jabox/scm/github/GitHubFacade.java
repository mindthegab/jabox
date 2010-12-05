package org.jabox.scm.github;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.PostMethod;

public class GitHubFacade {

	/**
	 * 
	 * @param username
	 * @param token
	 * @param repository
	 *            The repository name
	 * @return true if the Repository was created, false otherwise
	 */
	public static boolean createRepowithApi(String username, String token,
			String repository) {
		return createRepo(username + "/token", token, repository);
	}

	private static boolean createRepo(String username, String password,
			String repository) {
		HttpClient client = new HttpClient();
		client.getState().setCredentials(null, null,
				new UsernamePasswordCredentials(username, password));
		client.getState().setAuthenticationPreemptive(true);

		String uri = "http://github.com/api/v2/yaml/" + "repos/create";
		PostMethod post = new PostMethod(uri);
		post.setDoAuthentication(true);

		post.setParameter("name", repository);
		try {
			int result = client.executeMethod(post);
			System.out.println("Return code: " + result);
			for (Header header : post.getResponseHeaders()) {
				System.out.println(header.toString().trim());
			}
			System.out.println(post.getResponseBodyAsString());
		} catch (HttpException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			post.releaseConnection();
		}
		return true;
	}
}
