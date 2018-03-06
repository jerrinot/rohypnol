# Rohypnol
Feed your junit tests with rohypnole and reveal race conditions!

The idea is to use a Java-agent to insert randomized pauses into your code. This should reveal race-conditions.

## Usage:
Start your JVM with `-javaagent:<path>/rohypnol-1.0-SNAPSHOT-allinone.jar`
  
## Configration:
Currently hard-coded. It instruments only classes starting with `com.hazelcast` and parameters are in [Constants](https://github.com/jerrinot/rohypnol/blob/f702a6f4e1d070a2c07b7f48c9f8ed948628122b/src/main/java/info/jerrinot/rohypnol/Constants.java#L5)

## TODO:
- Configurable strategy to select classes & methods to instrument.
- Configurable {White/Black}list to feed the pill to selected threads only.
- Multiple strategies to generate sleep duration. Currently it always uses uniform distribution.

## Credits:
[@Holmistr](https://github.com/Holmistr) came-up with the idea. Implementation bugs are mine. 
