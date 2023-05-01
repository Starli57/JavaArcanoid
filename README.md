# JavaArcanoid | No Engine
This is a Java-based implementation of the classic arcanoid game. The game was built from scratch using Java without any game engine. It features a custom component system, similar to what is found in popular game engines like Unity. This makes it easy to create game objects with different properties and behaviors.

Sources does not include the library files, so it can't be compiled

# Video 
https://youtu.be/ahxBQ7GTVNE


# Tech debt
architecture
- dependency injection
- objects pool 

level
- serialize\deserialize level into json
- level editor

physics
- 2d raycast
- check ball collision with raycasts instead of collider overlap
- better algorithm to calculate normals

auto tests
