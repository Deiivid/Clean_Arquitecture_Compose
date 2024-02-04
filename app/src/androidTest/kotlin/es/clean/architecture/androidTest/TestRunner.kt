package es.clean.architecture.androidTest

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class TestRunner : AndroidJUnitRunner() {

    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        // Aquí puedes modificar la clase de la aplicación si es necesario antes de que se inicien las pruebas
        return super.newApplication(cl, className, context)
    }

    override fun onStart() {
        // Puedes realizar configuraciones iniciales aquí
        super.onStart()
    }

    // Puedes sobrescribir otros métodos si necesitas realizar más configuraciones personalizadas
}
