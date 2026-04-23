#Akshata Padalkar(anp178) U5A1
class Ball ():
  #constructor
  def __init__(self, number, color, striped, loc1, loc2):
    self.number = number
    self.color = color
    self.striped = striped
    if 0 <= loc1 <= 1 and 0 <= loc2 <= 2:
      self.location = (round(loc1, 2), round(loc2, 2))
    else:
      self.location = (0.5, 0.5)

  def getNumber(self):
    return self.number
  def setNumber(self, number):
    self.number = number

  def getColor(self):
    return self.color
  def setColor(self, color):
    self.color = color

  def getStriped(self):
    return self.striped
  def setStriped(self, striped):
    self.striped = striped

  def getLocation(self):
    return self.location
  def setLocation(self,loc1,loc2):
    if 0 <= loc1 <= 1 and 0 <= loc2 <= 2:
      self.location = (round(loc1, 2), round(loc2, 2))

  def distance(self, other, y=None):
    x1, y1 = self.location
    try:
        # assume Ball
        x2, y2 = other.location
    except:
        # otherwise tuple
        x2, y2 = other,y
    distance = ((x2 - x1)**2 + (y2 - y1)**2)**0.5
    return round(distance, 2)

# Testing
ball9 = Ball(9, "Red", True, 0.5, 0.5)
# Ball 9 attributes
print("Ball 9:")
print("Number:", ball9.getNumber())
print("Color:", ball9.getColor())
print("Striped:", ball9.getStriped())
print("Location:", ball9.getLocation())
print()  # spacing

ball7 = Ball(7, "Green", True, 0.5, 0.5)
# Ball 7 attributes
print("Ball 7:")
print("Number:", ball7.getNumber())
print("Color:", ball7.getColor())
print("Striped:", ball7.getStriped())
print("Location:", ball7.getLocation())
print()
ball9.setLocation(1, 2)  
ball7.setLocation(3, 4)   

# updated locations
print("After setting locations:")
print("Ball 9 Location:", ball9.getLocation())  
print("Ball 7 Location:", ball7.getLocation())  
print()

# Distance between balls
dist = ball9.distance(ball7)
print("Distance between Ball 9 and Ball 7:", dist)
