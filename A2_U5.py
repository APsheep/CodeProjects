from ball import Ball
import random
holes = [(0,0), (0,1), (0,2), (1,0), (1,1), (1,2)]

balls = [
    Ball(1, "Yellow", False, 0.5, 0.5),
    Ball(3, "Blue", False, 0.5, 0.5),
    Ball(5, "Orange", False, 0.5, 0.5),
    Ball(7, "Green", True, 0.5, 0.5),
    Ball(9, "Red", True, 0.5, 0.5)
]

for ball in balls:
    x = round(random.uniform(0, 1), 2)
    y = round(random.uniform(0, 2), 2)
    ball.setLocation(x, y)

for ball in balls:
    print(f"Ball {ball.getNumber()} location: {ball.getLocation()}")

ball9 = None
for ball in balls:
    if ball.getNumber() == 9:
        ball9 = ball
        break

def closestBall(target_ball, balls):
  min_dist = float('inf')
  closest = None

  for ball in balls:
    if ball != target_ball:
        d = target_ball.distance(ball)
        if d < min_dist:
            min_dist = d
            closest = ball

  return closest, min_dist

cb, dist_ball = closestBall(ball9, balls)
print(f"Ball {cb.getNumber()} at distance {dist_ball}")

def closestHole(target_ball, holes):
  min_dist = float('inf')
  closest = None

  for hole in holes:
    x,y = hole
    d = target_ball.distance(x,y)
    if d < min_dist:
      min_dist = d
      closest = hole

  return closest, min_dist

ch, dist_hole = closestHole(ball9, holes)
print(f"{ch} at distance {dist_hole}")
