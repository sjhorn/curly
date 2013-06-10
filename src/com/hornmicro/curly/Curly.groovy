package com.hornmicro.curly

import groovy.transform.CompileStatic

import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.databinding.swt.SWTObservables
import org.eclipse.swt.widgets.Display

import com.hornmicro.curly.ui.MainController
import com.hornmicro.util.MainThreader

@CompileStatic
class Curly {
	
	Display display
    MainController controller
    
    public Curly() {
        Display.appName = "Curly"
        display = new Display()
        controller = new MainController()
    }
    
    void run() {
        Realm.runWithDefault(SWTObservables.getRealm(display), controller)
        display.dispose()
    }
    
    static main(args) {
        MainThreader.run {
            new Curly().run()
        }
    }

}
