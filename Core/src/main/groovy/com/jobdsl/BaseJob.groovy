package com.jobdsl

class BaseJob {

	def dslFactory
	def jobConfig
	def tool
	def permissions
	def jobName
	
	BaseJob(dslFactory, jobConfig) {
		this.dslFactory = dslFactory
		this.jobConfig = jobConfig
		
		this.tool = jobConfig.'project.buildTool'
		
		this.jobName = "${jobConfig.'jenkins.rootFolder'}/${jobConfig.'job.baseName'}"
		
	}
	
	def jobBaseInstructions(jobContext) {
		
		jobContext.with {
			
			properties {
				rebuild {
					autoRebuild()
				}
			}
		}
	}
}
