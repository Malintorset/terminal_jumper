# Manual tests for Terminal Jumper

## Test 1 – Test player movement (left/right)

### Goal:

- Verify that the player moves left and right using both the keyboard and arrow keys.

### Method:

Before all: Launch game and press start.

- Press “a” and verify that the player moves left.
- Press left arrow and verify that the player moves left.
- Press “d” and verify that the player moves right.
- Press right arrow and verify that the player moves right.

### Result:

- Pass ✅

## Test 2 – Test player movement (jump)

### Goal:

- Verify that the player jumps when pressing spacebar.

### Method:

Before all: Launch game and press start.

- Press spacebar and verify that the player jumps.
- Verify that the player falls.

### Result:

- Pass ✅

## Test 3 – Test player movement (double jump)

### Goal:

- Verify that the player can jump again when pressing spacebar when in the air, as long as double jump is unlocked.

### Method:

Before all: Launch game and press start.

- Acquire a double-jump powerup, denoted in-game with a yellow ball with a lightning-bolt on it.
- Press spacebar.
- While player is in the air, press spacebar again and verify that the player jumps again.
- Verify that the player falls.

### Result:

- Pass ✅

## Test 4 – Test double jump limit

### Goal:

- Verify that the double jump only works once while in the air

### Method:

Before all: Launch game and press start.

- Acquire a double-jump powerup, denoted in-game with a yellow ball with a lightning-bolt on it.
- Press spacebar.
- While player is in the air, press spacebar multiple times and verify that the player jumps only once more.
- Verify that the player falls.

### Result:

- Pass ✅

## Test 5 – Verify main menu screen showing

### Goal:

- Verify that the main menu screen shows after loading/starting the game

### Method:

- Launch game and verify that the correct main menu screen is showing

### Result:

- Pass ✅

## Test 6 – Verify main menu button actions

### Goal:

- Verify that the correct action happens when clicking buttons on main menu screen

### Method:

Before all: Launch game and verify that the correct main menu screen is showing

- Click `Start` and verify that the main menu screen disappears and that the `mode select screen`` shows.
- Click `Quit` and verify that the game terminates.
- Click the `settings` button, visualized with a "gear" symbol, and verify that the `settings screen` shows.

### Result:

- Pass ✅

## Test 7 - Verify back button action

### Goal:

- Verify that you come back to the correct screen when clicking the back button.

### Method:

Before all: Launch game and enter the `how to play` screen.

- Click ‘back’ and verify that you go back to the previous screen.

### Result:

- Pass ✅

## Test 8 – Verify death and game over screen

### Goal:

- Verify that the player dies when supposed to and correct game over screen shows.

### Method:

Before each: Launch game and press start.

- Jump on a couple of platforms, then fall down and out of the camera view. Verify that the player dies and the game over screen shows.
- Stay still. Verify that the player dies when the camera view has moved some distance past the player. Verify that the game over screen shows.
- Verify that the final score is displayed.

### Result:

- Pass ✅

## Test 9 – Verify that the correct soundtrack is playing

### Goal:

- Verify that the soundtrack ‘Space-Jazz.mp3’ plays on main menu screen and ‘Wallpaper.mp3’ plays after starting the game.

### Method:

- Launch game and verify that the soundtrack ‘Space-Jazz.mp3’ plays while on the main menu screen.
- Click ‘start’ and verify that the soundtrack ‘Wallpaper.mp3’ plays while in game.

### Result:

- Pass ✅

## Test 10 – Verify object collision

### Goal:

- Verify that the player interacts with objects on screen as expected

### Method:

Before all: Launch game and press start.

- Verify that player can stand on the ‘ground’.
- Move player to each edge side of screen. Verify that player do not move past screen edges.
- Jump on a platform and verify that player can stand on the platform.

### Result:

- Pass ✅

## Test 11 – Verify graphics

### Goal:

- Verify that the correct graphical components render as expected.

### Method:

- Launch game and verify that the graphical components are rendering correctly on main menu screen:
- Background: old windows_desktop
- Logo: Terminal Jumper logo
- Buttons: start, quit, how to play and settings button
- Press start and verify that the graphical components are rendering correctly on game screen:
- Background: black terminal (at first, changes when threshold met)
- Sprite: sprite character (changes when doing actions (moving))
- Objects: platforms, bombs, power ups, power downs.
- Press settings and verify that the graphical components are rendering correctly on settings screen.

### Result:

- Pass ✅

## Test 12 – Verify looping of map

### Goal:

- Verify that the map is infinite, where platforms and other objects keep spawning, and that the game continues to funciton.

### Method:

Before all: Launch game and press start.

- Play the game for some amount of time until you can recognize the same pattern in platform placement, or to a point where pickups on screen visibly respawn.
- Verify that you can continue playing the game like previously
- Verify that new powerups, extra score pickups and power downs still appear on platforms
- Verify that the mouse pointer enemy continues chasing after you

### Result:

- Pass ✅

## Test 13 - Verify player can collect power-ups

### Goal:

- Verify that the player can collect power-ups when they come into contact with them.

### Method:

Before all: Launch game and press start.

- Place a power-up on a platform and verify that the player can collect it by jumping into it.
- Verify that the player's score is increased.

### Result:

- Pass ✅

## Test 14 – Test power downs

### Goal:

- Verify that the player's abilities are negatively affected when they collide with a power down object.

### Method:

Before all: Launch game and press start.

- Collect a power down object and verify that the player's speed is decreased.
- Collect multiple power downs and verify that the player's abilities continue to be negatively affected.
- Verify that the effect wears off after the timeout period is over, as denoted by the red timer on the left side of the screen.

### Result:

- Pass ✅

## Test 15 - Verify game difficulty increases over time

### Goal:

- Verify that the game becomes more difficult over time by increasing the speed of the platforms and/or spawning more bombs.

### Method:

Before all: Launch game and press start.

- Play the game for a set amount of time (e.g. 3 minutes) and verify that the platforms/camera view is moving faster.

### Result:

- Pass ✅

## Test 16 - Verify that the player can jump through platforms from below

### Goal:

- Verify that the player can jump through platforms from below.

### Method:

Before all: Launch game and press start.

- Jump into a platform from below and verify that the player passes through it, and is not blocked.

### Result:

- Pass ✅

## Test 17 - Verify player changes color when hitting power down object

### Goal:

- Verify that the player temporarily changes color (to red) when they collide with a power down object.

### Method:

Before all: Launch game and press start.

- Collect a power down object and verify that the player's color changes to red.
- Verify that the effect dissappears after the power down expires, as denoted by the red timer on the left hand side of the screen

### Result:

- Pass ✅

## Test 18 - Verify power up and power down objects/icons disappear after collision

### Goal:

- Verify that the power up and power down objects/icons disappear after the player collides with them.

### Method:

Before all: Launch game and press start.

- Collect a power up object and verify that it disappears after collision.
- Collect an extra score object and verify that it disappears after collision.
- Collect a power down object and verify that it disappears after collision.

### Result:

- Pass ✅

## Test 19 - Verify hitboxes placement and size

### Goal:

- Verify that the hitboxes for the player, platforms, power ups, power downs, and other objects are placed correctly and are of the correct size.

### Method:

Before all: Launch game and press start.

- Attempt to land on a platform verify that the player's and objects' hitboxes are placed correctly and are of the expected size, according to the visual size of the objects.
- Interact with various objects (power ups, extra score pickups and power downs) and verify that the player's and objects' hitboxes are placed correctly and are of the expected size, according to the visual size of the objects.
- Let the mouse pointer enemy catch you and verify that the hitbox is visually correct, i.e. can kill the player by hitting him anywhere, not just the feet.

### Result:

- Fail ❌

## Test 20 - Verify timer for power down effects

### Goal:

- Verify that the timer is working correctly for how long the player's abilities are negatively affected after hitting a power down object.

### Method:

Before all: Launch game and press start.

- Collect a power down object and start a timer.
- Verify that the player's abilities are negatively affected for the correct amount of time before returning to normal.

### Result:

- Pass ✅

## Test 21 - Verify sound effects when jumping, falling, and interacting with power ups and downs

### Goal:

- Verify that the correct sound effects play when the player is jumping and interacting with power ups, power downs and extra-score objects.

### Method:

Before all: Launch game and press start.

- Jump and verify that the correct sound effect plays.
- Collect a power up object and verify that the correct sound effect plays.
- Collect an extra-score object and verify that the correct sound effect plays.
- Collect a power down object and verify that the correct sound effect plays.

### Result:

- Pass ✅

## Test 22 - Verify volume changes in settings

### Goal:

- Verify that the volume changes when adjusted in the settings menu.

### Method:

Before all: Launch game and verify that the correct main menu screen is showing.

- Click 'settings' and verify that the 'settings screen' shows.
- Adjust the volume slider to a lower level and verify that the volume of the game (e.g., music, sound effects) decreases.
- Adjust the volume slider to a higher level and verify that the volume of the game (e.g., music, sound effects) increases.
- Return the volume slider to its original position and verify that the volume returns to its original level.

### Result:

- Pass ✅

## Test 23 - Verify gravity

### Goal:

- Verify that the player can fall off a platform.

### Method:

Before all: Launch game, press start and jump on a platform.

- Move off a platform. 
- Check if the player falls.

### Result:

- Pass ✅

## Test 24 - Verify that the mouse pointer enemy functions correctly

### Goal:

- Verify the mouse chases after the player, and kills the player upon contact with the player's feet.

### Method:

Before all: Launch game, press start.

- Move around and jump on platforms.
- Check that the mouse pointer turns towards the player and heads towards the player's position.
- Stand still and let the mouse pointer hit your feet.
- Verify that the game ends and the game over screen shows.

### Result:

- Pass ✅
