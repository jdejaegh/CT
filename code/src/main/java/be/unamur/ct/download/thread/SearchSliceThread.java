package be.unamur.ct.download.thread;

import be.unamur.ct.download.model.Slice;
import be.unamur.ct.download.service.ServerService;


/**
 * Thread class to download logs from a slice of a server
 */
public class SearchSliceThread extends Thread {

    private Slice slice;
    private ServerService serverService;


    /**
     * Constructor
     *
     * @author Jules Dejaeghere
     * @param slice         Slice to download logs from
     * @param serverService Reference of the ServerService to use
     * @see Slice
     */
    public SearchSliceThread(Slice slice, ServerService serverService) {
        super("Process - Slice #" + slice.getId());
        this.slice = slice;
        this.serverService = serverService;
    }


    /**
     * Starts the downloading process for the slice saved in the variables of the instance
     *
     * @author Jules Dejaeghere
     */
    @Override
    public void run() {
        serverService.searchSlice(slice);
    }
}
