package com.jobdsl

import groovy.transform.InheritConstructors

@InheritConstructors
class BuildJob extends BaseJob{

	def job() {
		
		this.dslFactory.job(jobName) {
			super.jobBaseInstructions(it)
			consurrentBuild()
			
		}
	}
	
}
