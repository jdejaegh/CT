package be.unamur.ct.download.thread;


import be.unamur.ct.download.model.Server;
import be.unamur.ct.download.service.ServerService;


/**
 * Thread class to start the downloading process for a give server
 */
public class ScanLogThread extends Thread {

    private Server server;
    private ServerService serverService;


    /**
     * Constructor
     *
     * @author Jules Dejaeghere
     * @param server        Server to download logs from
     * @param serverService Reference to the ServerService to use
     * @see Server
     */
    public ScanLogThread(Server server, ServerService serverService) {
        super("Scan - " + server.getNickname());
        this.server = server;
        this.serverService = serverService;
    }


    /**
     * Starts the downloading process for the server saved in the variables of the instance
     *
     * @author Jules Dejaeghere
     */
    @Override
    public void run() {
        serverService.startSearch(server);
    }

}
