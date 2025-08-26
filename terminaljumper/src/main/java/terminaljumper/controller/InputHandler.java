package terminaljumper.controller;

import terminaljumper.objects.Player;

/**
 * <h1>InputHandler</h1>
 * The InputHandler interface provides a contract for checking and dealing with a user's keyboard input.
 */
public interface InputHandler {

    /**
     * This method manages the logic behind calling other internal functions.
     */
    void checkInput();

    /**
     * This method checks for inputs related to system functions, like "ESC" to quit the game.
     */
    void handleMiscInput();

    /**
     * This method checks for user input and updates the player's velocity and state
     * accordingly.
     * If the "D" or "RIGHT" key is pressed, the player moves to the right.
     * If the "A" or "LEFT" key is pressed, the player moves to the left.
     * If the "SPACE" or "UP" key is pressed, the player jumps, either performing a double
     * jump if enabled or a regular jump if grounded.
     * The method also applies a linear impulse to the player's body to simulate the
     * jump and sets the "isGrounded" and "doubleJumpEnabled" flags accordingly.
     */
    void handlePlayerInput();

    /** Updates the Player object that InputHandler handles input for */
    void setPlayer(Player player);
}
