import java.io.File
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.Transferable
import java.awt.image.BufferedImage 
import javax.imageio.ImageIO
import java.util.UUID

object Clip {

  def genFileName (): String = {
     UUID.randomUUID.toString + ".png"
  }

  def getClipboard (): Clipboard = {
    Toolkit.getDefaultToolkit().getSystemClipboard()
  }

  def showClipboardMimeTypes (cpl: Clipboard) = {
    cpl.getAvailableDataFlavors().map(_.getPrimaryType()).toSet
  }

  def getContent(cpl: Clipboard): Option[Transferable] = {
    Option(cpl.getContents(null))
  }


  def getImage(cpl: Clipboard): Option[BufferedImage] = {
    getContent(cpl)
      .filter(_.isDataFlavorSupported(DataFlavor.imageFlavor))
      .map(_.getTransferData(DataFlavor.imageFlavor).asInstanceOf[BufferedImage])
  }

  def saveClipboardImage(filename: String){
    val imgOpt = getImage(getClipboard())
    val ext    = filename.split("\\.").last

    imgOpt match {
      case None      => println("Error: No image available in clipboard.")
      case Some(img) => val file = new File(filename)
        ImageIO.write(img, ext, file)
        println("file:" + filename)
    }
  }

  def saveClipboardImageUUID(directory: String){
    val filename = (new File(directory, genFileName())).toString
    //println("File name = " + filename)      
    saveClipboardImage(filename)
  }

  def processArguments(arg0: String, arg1: String) = {
    arg0 match {
      case "-uuid"  =>  saveClipboardImageUUID(arg1)
      case "-file"  =>  saveClipboardImage(arg1)
      case  _       =>  println("Error: Invalid option.")
    }
  }

  def main(args: Array[String]): Unit = {
    args.length match {
      case  0  => println("Error: Invalid option")
      case  2  => processArguments(args(0), args(1))  //saveClipboardImageUUID(args(0))
      // saveClipboardImage(args(0))
      case  _  => println("Error: Invalid option")
    }
  }

}
