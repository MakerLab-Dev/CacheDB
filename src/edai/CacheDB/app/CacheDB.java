package edai.CacheDB.app;

import edai.CacheDB.Cache;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "cachedb", version = "CacheDB 1.0", description = "A simple key-value store with persistence.")
class CacheDB implements Callable<Integer> {

    @Option(names = {"-d", "--dir"}, description = "directory where the cache will be stored (Defaults to ./)")
    private String dir = "./";

    @Command(name = "get", description = "Get the value associated with the key passed as argument.")
    public Integer get(@Parameters(arity = "1", paramLabel = "Key", description = "Key to use in the command.") String key) {
        Cache cache = new Cache(dir);
        try {
            System.out.println(cache.get(key));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "add", description = "Add or update the value associated to a key.")
    public Integer add(
            @Parameters(arity = "1", paramLabel = "Key", description = "Key to use in the command.") String key,
            @Parameters(arity = "1", paramLabel = "Value", description = "Value to use in the command.") String value) {
        Cache cache = new Cache(dir);
        try {
            cache.put(key, value);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "remove", description = "Remove a key from the store.")
    public Integer remove(@Parameters(arity = "1", paramLabel = "Key", description = "Key to use in the command.") String key) {
        Cache cache = new Cache(dir);
        try {
            cache.remove(key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "clear", description = "Remove all keys from the store.")
    public Integer clear() {
        Cache cache = new Cache(dir);
        try {
            // Removing every key it's very inefficient, instead we remove the whole directory
            cache.clear();
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "size", description = "Count the number of keys stored in cache.")
    public Integer size() {
        Cache cache = new Cache(dir);
        try {
            System.out.println("Total stored Strings in cache: " + cache.size());
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "keys", description = "Get all keys stored in cache.")
    public Integer keys() {
        Cache cache = new Cache(dir);
        try {
            System.out.println("Listing keys stored in cache:");
            String[] keys = cache.getAll();
            for (String key : keys) {
                System.out.println(key);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Override
    public Integer call() throws Exception {
        System.out.println("Subcommand needed: 'get', 'add' or 'remove'. Additional subcommands are: 'clear', 'size', 'keys'.");
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new CacheDB()).execute(args);
        System.exit(exitCode);
    }
}
