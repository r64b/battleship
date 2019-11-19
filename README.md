# battleship
basic battleship realisation with few tweaks in rules

Rules:
 * 1. Each player has a playing field of 10x10 "squares".
 * Players choose how they arrange their ships on the playing field.
 * Each player has at least 10 ships, with a total length of at least 20 "squares".
 * Ships can come in contact with faces and intersect with each other.
 *
 * 2. Starting from the first player, each in turn “makes a shot” - selects a “square” on the enemy field.
 * The player cannot repeatedly "shoot" in the same "square".
 * if there was a ship in this “square”, that part of it that was in this “square” is “flooded”.
 * when all points of the ship are “flooded”, the ship “explodes”, and a “shot” passes into each “square” around the ship.
 *
 * 3. For each "blown up" enemy ship, the player receives one point.
 * The game ends as soon as one of the players scores 10 points.
