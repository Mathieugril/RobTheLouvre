<h1># RobTheLouvre</h1>

<h2>Game Overview</h2>
<h3>1. Aspects</h3>

       Setting: The Louvre Museum, 19th October 2025.
       Goal: Rob Nepolianic Jewels and Escape.
       Tone: Quick-thinking, Stealth Game.


<h3>2. Game Components</h3>

       Rooms: Balcony - Starting point - Exits: Regalia Gallery | Garden
              Main Gallery - Key area - Exits: Balcony | Old Masters Gallery | Control Room
              Janitors Closet - Transtion point - Exits: Guard Break Room | Maintence Hall | Secret Passage

       Objects: Keycard - Used on high clearance doors
                Uniform - Used for disguise
                Crown Jewels - Prized possession

       Characters: Public - Used to blend in
                   Security Guards - Loud mouths that hold keycards

       Challenges: Some rooms require a keyard, a disguise or both - failure to meet requirments causes player to get caught


<h3>3. Map</h3>
<img width="802" height="655" alt="image" src="https://github.com/user-attachments/assets/3ddd2bdf-6f7a-40bc-9db9-f619ffd18808" />



<h2>Game Design</h2>
  
      Class Name                     Attributes                                          Methods
       Character                 name, currentRoom, inventory            getName, getCurrentRoom, getInventory, pickUpItem, dropItem
       Items             name, description, location, id, isVisible   getDescription, getName, getLocation, getId, isVisible     
       Room        description, exits, details, items       inspect, getDescription, getExit, searchRoom, removeItem, addItem, getItems, getLongDescription
       ZorkULGame                     parser, player              createRooms, play, printWelcome, processCommand, printHelp, goRoom, details
       Text                                                              Descripstions, Details, ItemDESC, Convos
       Cameras                     room, isOn                            getStatus, setStatus
       Guards                     - Extends Character -                             lie
       Util                                                                      checkItemAvailable


<h2>Relationships</h2>






