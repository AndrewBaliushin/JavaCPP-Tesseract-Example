#JavaCPP + Tesseract example

Remake of official [example](https://github.com/bytedeco/javacpp-presets/tree/master/tesseract) from JavaCPP. 

BufferedImage used to create byte array. Then PIX object created from byte array.

Example uses png. 

To run example:
`mvn package exec:java -Dexec.mainClass=BasicExample`

### Known issue
Error: Illegal min or max specification!
Bug in tesseract native library. 
Fix: set numeric locale before running the example. 
`export LC_NUMERIC="C"` 
