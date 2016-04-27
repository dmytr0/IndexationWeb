package xyz.dimonick.Services;

import java.util.TimerTask;

public class DownloadScheduler extends TimerTask{

    private static Solution ic = Solution.getInstance();



    @Override
    public void run() {

        ic.setIndexes(IndexDownloader.getIdexes());

    }
}
