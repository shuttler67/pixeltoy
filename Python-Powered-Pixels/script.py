import math

setListenerOrientation(0, 1, 1)
testStream = Source("res/Rayman_2_music_sample.ogg", "stream", True)
testWav = Source("res/center.wav")
testWav.play()

setBackgroundColour(0, 191, 255)

class Circle:
    def __init__(self, radius):
        self.wireFrame = random() < 0.5
        self.radius = radius
    def draw(self, x, y, r):
        setWireFrame(self.wireFrame)
        drawCircle(x, y, self.radius)
    def getRadius(self):
        return self.radius

class Rectangle:
    def __init__(self, w, h):
        self.wireFrame = random() < 0.5
        self.w = w
        self.h = h

    def draw(self, x, y, r):
        setWireFrame(self.wireFrame)
        drawRectangle(x, y, self.w, self.h, r)
    def getRadius(self):
        if self.w < self.h:
            return self.h
        else:
            return self.w

class Ellipse:
    def __init__(self, w, h):
        self.wireFrame = random() < 0.5
        self.w = w
        self.h = h

    def draw(self, x, y, r):
        setWireFrame(self.wireFrame)
        drawEllipse(x, y, self.w, self.h, r)
    def getRadius(self):
        if self.w < self.h:
            return self.h
        else:
            return self.w

class RegularPolygon:
    def __init__(self, order, radius):
        self.wireFrame = random() < 0.5
        self.order = order
        self.radius = radius

    def draw(self, x, y, r):
        setWireFrame(self.wireFrame)
        drawRegularPolygon(x, y, self.order, self.radius, r)
    def getRadius(self):
        return self.radius

class Polygon:
    def __init__(self, *points):
        self.wireFrame = random() < 0.5
        self.points = points
        i = 0
        x = 0
        greatestDist = 0
        for p in points:
            i += 1
            if i % 2 == 0:
                dist = x**2 + p**2
                if dist > greatestDist:
                    greatestDist = dist
            else:
                x = p

        self.radius = math.sqrt(greatestDist)

    def draw(self, x, y, r):
        setWireFrame(self.wireFrame)
        GLpush()
        GLtranslate(x, y)
        drawPolygon(self.points, r)
        GLpop()
    def getRadius(self):
        return self.radius

class StringS:
    def __init__(self, text, size):
        self.text = text
        self.size = size
    def draw(self, x, y, r):
        drawString(x, y, self.text, rotation = r, size = self.size)
    def getRadius(self):
        return getStringDimensions(self.text)[0]

ballArray = []

def HSVtoRGB(h, s, v):
    s /= 100
    v /= 100

    c = v * s
    x = c * (1-abs((h/60) % 2 - 1))
    m = v - c

    if h < 60:
        rgb = (c + m, x + m, m)
    elif h < 120:
        rgb = (x + m, c + m, m)
    elif h < 180:
        rgb = (m, c + m, x + m)
    elif h < 240:
        rgb = (m, x + m, c + m)
    elif h < 300:
        rgb = (x + m, m, c + m)
    elif h < 360:
        rgb = (c + m, m, x + m)

    return rgb[0]*255, rgb[1]*255, rgb[2]*255

class RandomShape:
    x = 0
    y = 0
    ySpeed = 0.0
    red = 0.0
    green = 0.0
    blue = 0.0

    def __init__(self):
        self.timer = (random() * 104) + 101
        self.generateSpeeds()
        self.generateColour()

        self.generateShape()
        self.radius = self.shape.getRadius()

    def draw(self):
        self.ySpeed -= 0.15
        self.x += self.xSpeed
        self.y += self.ySpeed
        self.rotation += self.rotationSpeed
        useColour(self.red, self.green, self.blue, self.timer)
        self.shape.draw(self.x, self.y, self.rotation)

        if self.x - self.radius > _screenWidth:
            self.x -= _screenWidth + 2 * self.radius
        if self.x + self.radius < 0:
            self.x += _screenWidth + 2 * self.radius
        if((self.y - self.radius < 0) & (self.ySpeed < 0)):
            self.ySpeed *= -0.95
            self.y = self.radius
            self.timer -= 5
            if self.timer < 0:
                return False
        return True

    def generateShape(self):
        rand = random()
        if rand < 1.0/6.0:
            self.shape = Circle((random() * 66) + 10)
        elif rand < 2.0/6.0:
            self.shape = Rectangle((random() * 70) + 10, (random() * 50) + 10)
        elif rand < 3.0/6.0:
            self.shape = Ellipse((random() * 70) + 10, (random() * 70) + 10)
        elif rand < 4.0/6.0:
            self.shape = RegularPolygon( int(random()*10)+3, random()*60+10)
        elif rand < 5.0/6.0:
            t = []
            for i in range(int(random()*8)*2+6):
                t.append( (random()-0.5) * 300.0)
            self.shape = Polygon( t)
        else:
            rand = random()
            self.shape = StringS( "lol" if rand < 0.25 else "meh" if rand < 0.5 else "boom" if rand < 0.75 else "corny" if rand < 0.98 else "WRATH OF THE MOM", random() * 15 + 1)
            if self.shape.text == "WRATH OF THE MOM":
                self.red = 255
                self.blue = 0
                self.green = 0
                self.timer = 255

    def generateColour(self):
        self.red, self.green, self.blue = HSVtoRGB(random() * 360, random()*30 + 70, random()*50+50)

    def generateSpeeds(self):
        self.x = random() * _screenWidth
        self.y = random() * _screenHeight
        self.ySpeed = 0
        self.xSpeed = (random() - 0.5) * 15.0
        self.rotation = random() * 360
        self.rotationSpeed = (random() - 0.5) * 15.0

testStream.play()

for i in xrange(0, 10):
    ballArray.append(RandomShape())

frameCounter = 0
while True:
    newFrame()
    frameCounter += 1
    if frameCounter % 40 == 0:
        for i in xrange(0, 10):
            ballArray.append(RandomShape())

    for i in xrange(len(ballArray)-1, -1, -1):
        if not ballArray[i].draw():
            del ballArray[i]

    if isKeyDown("r"):
        ballArray = []
    if isKeyDown("escape"):
        quit()
    if isRightMouseDown() or isLeftMouseDown():
        useColour(0,0,0)
        drawString(320, 240, "CLEECK", size = 8)