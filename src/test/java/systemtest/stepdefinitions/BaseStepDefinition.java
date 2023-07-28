package systemtest.stepdefinitions;

import framework.WebHelper;

public class BaseStepDefinition {

    private WebHelper webHelper;

    protected WebHelper web(){
        return getWebHelper();
    }

    private WebHelper getWebHelper(){
//        if(webHelper == null)
//            this.webHelper = new WebHelper();
//
//        return webHelper;

        return webHelper == null? this.webHelper = new WebHelper(): webHelper;
    }

}
