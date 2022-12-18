package edai.CacheDB.app;

import edai.CacheDB.Cache;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "cachedb", version = "CacheDB 1.0", description = "A simple key-value store with persistence.")
class CacheDB implements Callable<Integer> {

    @Parameters(index = "0", description = "Cache command to execute. Valid commands are: get, add, remove.")
    private String command;

    @Parameters(index = "1", description = "Key to use in the command.")
    private String key;

    @Parameters(index = "2", description = "Value to use in the command.")
    private String value;

    @Option(names = {"-d", "--dir"}, description = "directory where the cache will be stored (Defaults to ./)")
    private String dir = "./";

    @Override
    public Integer call() throws Exception {
        Cache cache = new Cache();
        try {
            switch (command.toLowerCase()) {
                case "get":
                    System.out.println(cache.get(key));
                    return 0;
                case "add":
                    cache.addNew(key, value);
                    return 0;
                case "remove":
                    cache.remove(key);
                    return 0;
                default:
                    System.out.println("Invalid command");
                    return -1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new CacheDB()).execute(args);
        System.exit(exitCode);
    }
}
