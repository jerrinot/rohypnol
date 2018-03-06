# Rohypnol
Feed your junit tests with rohypnole and reveal race conditions!

The idea is to use Java-agent to instrument bytecode to insert randomized pauses into your code. This should reveal race-conditions.

## Usage:
Start your JVM with `-javaagent:<path>/rohypnol-1.0-SNAPSHOT-allinone.jar`
  
## Configration:
Currently hard-coded. It instruments only classes starting with `com.hazelcast` and parameters are in [Constants](https://github.com/jerrinot/rohypnol/blob/f702a6f4e1d070a2c07b7f48c9f8ed948628122b/src/main/java/info/jerrinot/rohypnol/Constants.java#L5)
