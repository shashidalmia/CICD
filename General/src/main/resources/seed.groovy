import com.jobdsl.utils.ConfigProcessor
import hudson.*
import hudson.model.Executor

def dslDir = 'jobdsl/jc'
def cicdVersion = CICD_VERSION
def folderPrefix = FOLDER_PREFIX ? FOLDER_PREFIX.replaceAll("/\$", "") : CICD_VERSION
def isProductionSeed = false

def configProcessor = new ConfigProcessor(this, isProductionSeed, ARTIFACT_ID, CICD_VERSION, folderPrefix)

def jcDir = new FilePath(Executor.currentExecutor().getCurrentWorkspace(), dslDir)
def folderList = []

jcDir.list().each { configFile ->
	
	Map<String, Map> allJCs = configProcessor.loadSource(configFile.toString())
	allJCs.each { _, jc ->
		Class.forName(jc.'job.baseClassName').newInstance(this, jc).job()
	}
	
}

def folderListFile = new File("${WORKSPACE}/folder.list")
folderListFile.write("FOLDER_LIST=${folderList.unique().join(',')}")