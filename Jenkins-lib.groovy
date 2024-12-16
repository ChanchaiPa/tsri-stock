def _setModeFrontEnd(String environtmentFile) {
    //String greeting = "============>  Welcome...... " + workspace
    //println( greeting )
    String fileContents = ""
    def lines = new File( environtmentFile  ).readLines()
    for(line in lines){
        if (line.length()>16 && line.contains("export") && line.contains("const") && line.contains("env"))
            line = "export const env = env_config.UAT;"   
        fileContents += line + "\n"          
    }
    new File( environtmentFile ).write(fileContents.trim())    
}

def _setModeBackEnd(String applicationFile) {
    String fileContents = ""
    def lines = new File( applicationFile  ).readLines()
    for(line in lines){
        if (line.length()>23 && line.contains("spring.profiles.active") )
            line = "spring.profiles.active=uat"  
        fileContents += line + "\n"          
    }
    new File( applicationFile ).write(fileContents.trim())    
}

return this