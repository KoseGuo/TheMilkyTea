package com.kose.annotationcompiler

import com.google.auto.service.AutoService
import com.kose.annotation.BindActivity
import java.io.Writer
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class AnnotationCompiler : AbstractProcessor() {
    lateinit var filer: Filer

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        filer = processingEnv?.filer!!
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val set = HashSet<String>()
        set.add(BindActivity::class.java.canonicalName)
        return set
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return processingEnv.sourceVersion
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        val set = p1!!.getElementsAnnotatedWith(BindActivity::class.java)
        val map = HashMap<String, String>()
        set.forEach {
            val element = it as TypeElement
            val path = element.getAnnotation(BindActivity::class.java).path
            val activity = element.qualifiedName.toString()
            map[path] = "$activity.class"
        }

        if (map.size > 0) {
            val clzName = "ActivityUtil${System.currentTimeMillis()}"
            var writer: Writer? = null
            try {
                val sourceFiler = filer.createSourceFile("com.kose.utils.$clzName")
                writer = sourceFiler.openWriter()
                val sb = StringBuilder()
                sb.append(
                    "package com.kose.utils;\n" +
                            "\n" +
                            "import com.kose.router.IRouter;\n" +
                            "import com.kose.router.KRouter;\n" +
                            "\n" +
                            "public class $clzName implements IRouter {\n" +
                            "    @Override\n" +
                            "    public void putActivity() {\n")
                val iterator = map.keys.iterator()
                if (iterator.hasNext()) {
                    val path = iterator.next()
                    val activity = map[path]
                    sb.append("        KRouter.Companion.getInstance().addActivity(\"$path\", $activity);\n" )
                }
                sb.append(
                    "    }\n" +
                        "}"
                )
                writer.write(sb.toString())
            } catch (e: Exception) {

            } finally {
                writer?.close()
            }
        }
        return false
    }
}