import java.io.File
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.Transferable
import java.awt.image.BufferedImage 
import javax.imageio.ImageIO

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
                      println("Ok. Image saved file: " + filename)
  }  
}

// println(args(0))

def main () {
  args.length match {
    case  0  => println("Error: Invalid option")
    case  1  => saveClipboardImage(args(0))
    case  _  => println("Error: Invalid option")
  }
}

