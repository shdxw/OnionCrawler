package no.ueland.onionCrawler.services.onionHost;

import no.ueland.onionCrawler.objects.exception.OnionCrawlerException;

/**
 * Service to handle onion host data. Currently this only
 * stores online-status for a onion host. Possible future
 * features here will be tagging of hosts that leak their
 * real IP via some basic tests.
 */
public interface OnionHostService {

	/**
	 * Set online/offline status for a given host
	 * @param host
	 * @param online
	 * @throws OnionCrawlerException
	 */
	void setStatus(String host, boolean online) throws OnionCrawlerException;

	/**
	 * Returns the total number of known onion hosts
	 * @return
	 * @throws OnionCrawlerException
	 */
	int count() throws OnionCrawlerException;
}
