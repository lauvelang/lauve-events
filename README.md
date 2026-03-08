# lauve-events

lauve-events is a (*kind-of*\*) Fabric library that aims to add an event system similar to [Spigot](https://www.spigotmc.org/) or [Paper](https://papermc.io/)'s.

\**It doesn't actually use any Fabric APIs directly, but it's packaged as a Fabric mod.*

`lauve-events`, as the name suggests, is part of the [Lauve](https://github.com/lauvelang) project.

## Testing

Minecraft's GameTest system is used to make sure events are called and applied correctly. See `src/gametest` for how these are implemented.

## Contributing

To add an event, you'll want to:

1. Create an event class in a package best fitting its category (an event fired when the player moves is in the `player` package)
   1. If you are unsure on where to put an event, make your best guess and it will be discussed before your PR is merged.
2. Write a proper Javadoc explaining the event
   1. Please describe exactly when the event is fired, along with any other important details like if it is fired off-thread or if it is called super often.
   2. Make sure there is a `@see` tag linking to the mixin that fires it.
   3. Optionally, add an `@author` tag, so we can see who the lovely person was that contributed. <3
3. Add a mixin that posts the event. Make sure any fields that users may want to change can be!
4. Register it in `LauveEvents`
   1. The name should generally be snake case (`player_chat`)
5. Write a GameTest ensuring that your event runs correctly.
   1. If possible, make sure any modifications work (ex. setting the contents of a chat message)
   2. While the other steps are mandatory, we won't reject a PR if everything *but* this is implemented. It would be really appreciated, though!

Some other notes:

- Make use of Lombok annotations when appropriate.
- Don't use any loader-specific APIs to call events unless the mod can be compiled without them. In the future, Lauve may be ported to other platforms.
- Obviously, don't modify any game functionality by default. This is a library mod!
