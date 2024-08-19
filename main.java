import java.io.File;
import java.util.concurrent.RecursiveTask;

public class FileCounter extends RecursiveTask<Integer> {
    private final File directory;

    public FileCounter(File directory) {
        this.directory = directory;
    }

    @Override
    protected Integer compute() {
        int count = 0;
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                FileCounter task = new FileCounter(file);
                task.fork();
                count += task.join();
            } else {
                count++;
            }
        }
        return count;
    }
}
