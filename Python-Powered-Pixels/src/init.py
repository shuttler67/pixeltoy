from graphics import GraphicsController 
from graphics import Colour
from core import GameController 
from core import Input
from core import Timer
from java.util import Random
from audio import Source
from audio import AudioController
 
#internal variables. Please do not use them; they can change name and function at any time.
#TODO create random class
sys_gameController = GameController() 
sys_inputController = Input()
sys_random = Random()
sys_colour = Colour()

def setListenerPosition(x, y, z):
    AudioController.setListenerPosition(x, y, z)

def setListenerVelocity(x, y, z):
    AudioController.setListenerVelocity(x, y, z)

def setListenerOrientation(x, y, z, upaxis = "z"):
    AudioController.setListenerOrientation(x, y, z, upaxis)

def rewindAllSources():
    AudioController.rewindAll()

def stopAllSources():
    AudioController.stopAll()

def pauseAllSources():
    AudioController.pauseAll()

def setVolume(volume):
    AudioController.setVolume(volume)

def random(): 
    return sys_random.nextDouble()
 
def drawRectangle(x, y, width, height, rotation = 0):
    GraphicsController.drawRectangle(x, y, width, height, rotation)
 
def drawCircle(x, y, radius, rotation = 0):
    GraphicsController.drawCircle(x, y, radius)

def drawEllipse(x, y, radiusX, radiusY, rotation = 0):
    GraphicsController.drawEllipse(x, y, radiusX, radiusY, rotation)

def drawPolygon(*points, **options):
    if isinstance(points[0], tuple):
        if isinstance(points[0][0], list):
            GraphicsController.drawPolygon(points[0][0], points[1])
            return

    if 'rotation' in options:
        GraphicsController.drawPolygon(points, options["rotation"])
    else:
        GraphicsController.drawPolygon(points, 0)

def drawRegularPolygon(x, y, order, size, rotation = 0):
    if not order >= 3:
        raise Exception("A polygon must have more than two vertices, DUMBASS!")

    GraphicsController.drawRegularPolygon(x, y, int(order), size, rotation)
 
def drawLine(x1, y1, x2, y2):
    GraphicsController.drawLine(x1, y1, x2, y2)
 
def drawPoint(x, y):
    GraphicsController.drawPoint(x, y)
 
def drawString(x, y, string, size = 1, rotation = 0, centered = True):
    GraphicsController.drawString(x, y, str(string), size, rotation, centered)

def getStringDimensions(string):
    return GraphicsController.getStringDimensions(string)

def useColour(r, g, b, a = 255):
    sys_colour.useColour(r, g, b, a)

def setBackgroundColour(r, g, b):
    sys_colour.setBackgroundColour(r, g, b)

def setWireFrame(enabled):
    GraphicsController.setWireFrame(enabled)

def newFrame():
    sys_gameController.newFrame()

def quit():
    sys_gameController.quit()

def getTime():
    return Timer.getTime()

def isLeftMouseDown():
    return sys_inputController.isLeftMouseDown()
 
def isRightMouseDown():
    return sys_inputController.isRightMouseDown()
 
def getMouseWheelDelta():
    return sys_inputController.getMouseWheelDelta()
 
def isKeyDown(key):
    return sys_inputController.isKeyDown(key)
 
def loadImage(src, smooth = True, animatedImageCountX = 1):
    return GraphicsController.loadImage(src, smooth, animatedImageCountX)
 
def drawImage(image, x, y, width, height):
    GraphicsController.drawImage(image, x, y, width, height)

def GLpush():
    GraphicsController.GLpush()
def GLpop():
    GraphicsController.GLpop()
def GLtranslate( dx, dy):
    GraphicsController.GLtranslate(dx, dy)
def GLrotate(angle):
    GraphicsController.GLrotate(angle)
def GLscale(sx, sy):
    GraphicsController.GLscale(sx, sy)

#variables updated by backend
_mouseX = 0
_mouseY = 0 
_screenWidth = 640 
_screenHeight = 480 
 
sys_colour.resetColour()