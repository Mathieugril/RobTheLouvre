package com.robthelouvre.terminal;

import java.util.ArrayList;

public class Text {

    public static class Descriptions {
        public static final String BALCONY1 = "stand on a narrow balcony above the riverside façade and gardens. The cherry picker sits below, beside a large tree; a glass window ahead leads into the museum's upper wing.";
        public static final String BALCONY2 = "notice the cherry picker is nowhere to be seen.";
        public static final String LOBBY = "are in the grand pyramid lobby. Ticket desks, security scanners and the echo of footsteps fill the space.";
        public static final String REGALIA = "see a glittering hall of crown jewels and diadems behind glass cases. Spotlights and no public make this the obvious prize zone. Cameras cover *nearly* the whole room";
        public static final String MASTERS = "are in long gallery of paintings: tourists pause before masterpieces while guards linger at doorways. The steady flow of foot traffic provides cover.";
        public static final String SECURITY = "enter the control room. Monitors line the walls, each screen showing CCTV feeds of corridors, galleries, and exterior walls.";
        public static final String GUARDS = "enter a small break room with lockers, a coffee machine and a mini fridge. Footsteps echo through the thin walls.";
        public static final String SERVICE = "are under dim lights that hang over pipes and cables. OLd crates line the walls, this tunnel connects hidden zones beneath the museum.";
        public static final String JANITOR = "see buckets, mops and cleaning supplies in tight quarters. The old door is behind stacked carts.";
        public static final String DELIVERY = "see wide loading doors and a rubber-rimmed ramp open to the street. A delivery van often idles outside while staff have a smoke.";
        public static final String PASSAGE = "enter a hidden passage, a narrow corridor with a low ceiling and no light.";
        public static final String BASEMENT = "are in a dim underground garage: engine rumble, an exit ramp to the street and the faint hum of air-conditioning.";
        public static final String GARDEN = "are surrounded by hedges cut into formal shapes, a silent fountain and statues under lights. A large oak tree sits beside the balcony";
        public static final String VIP = "are impressed by luxurious sofas and low tables, champagne flutes and private doors to the gallery. Guests in tuxedos are quietly monitored by nearby security.";
        public static final String OUTSIDE = "have left the museum.";
        public static final String VAN = "are inside the Renault Traffic you found those keys for, ideal.";
    }

    public static class Details {
        public static final String BALCONY_DET1 = "Scuffed paint marks the window frame; the latch looks old and might give with some force.";
        public static final String BALCONY_DET2 = "The cherry picker was forced to move by angry locals, will have to find another way out";
        public static final String LOBBY_DET = "The sheer amount of people would make it easy to blend in. You see a guard not paying attention.";
        public static final String REGALIA_DET = "The room is illuminated via the window and spotlights. Two guards are taking a break on a bench on the other side of the hall, with all the displays it would be easy to get close without being spotted.";
        public static final String MASTERS_DET = "The paintings would be a good score but way to many people here. The guards seem to be talking about something over the radio to each-other";
        public static final String SECURITY_DET = "Screens show the camera feed of nearly the whole museum. Tobias is the supervisor today. Guards rotate in-and-out from break room, one forgets to logout...";
        public static final String GUARD_DET = "Half empty coffee cups are scattered on the counter, a card game is being played. Locker doors line the wall, one hangs ajar.";
        public static final String JANITOR_DET = "The cramped closet smells of bleach. No one is around at the moment";
        public static final String SERVICE_DET = "Pipes hiss and ducts vibrate above. Not much to see here it seems.";
        public static final String DELIVERY_DET = "Although quite open there is an annoying amount of guards around. It would be foolish to try anything without a disguise.";
        public static final String BASEMENT_DET = "Vans line the side wall. If done right they would be easy to hot wire in order to escape into the city.";
        public static final String GARDEN_DET = "A beaten path in the corner catches your eye. An old seemingly locked door hides at the end of the path.";
        public static final String PASSAGE_DET1 = "Old blueprints and staff scribbles mark this 'staff only' route. You cant go anywhere with out a form of light";
        public static final String PASSAGE_DET2 = "Old blueprints and staff scribbles mark this 'staff only' route, the flashlight lets you see.";
        public static final String VIP_DET = "Obnoxious tones and low lighting make these people and easy target, but be careful of the guards.";
        public static final String VAN_DET = "Surprisingly clean. A card for a with an address and 10:00h is written on it, you look at radio: 09:38, better not be late.";
    }

    public static class ItemDESC {
        public static final String KEYCARD = "Might come in handy for some rooms, others will require more.";
        public static final String UNIFORM = "This can be used to blend in throughout most of the museum.";
        public static final String GUM =  "Hollywood chewing gum, not much use other then it helps with fresh breath.";
        public static final String SMOKES = "Marlboro reds, probably helping him by taking these.";
        public static final String FLASHLIGHT = "Spare flashlight left near the guards, doesnt seem to be much battery left though.";
        public static final String CROWN = "Crown of Empress Eugénie, the prized possession of the first president and last monarch of France: Napoleon III.";
        public static final String HEADPHONES = "Beaten up pair of headphones. They Still work, barely.";
        public static final String WAFFLES = "His go-to snack.";
        public static final String SNUS = "Some might say he is addicted, he calls it a hobby.";
        public static final String MONSTER = "Energy drink he has daily.";
        public static final String BREAD = "Tupperware of brown bread with a bit of butter.";
        public static final String VANKEYS = "Keys for a 'Renault Traffic.'";

    }

    public static class Convos {
            public static ArrayList<String> regaliaConvo() {
                ArrayList<String> lines = new ArrayList<>();
                lines.add(" Patrice: ...I’m telling you, this new patrol schedule makes no sense.");
                lines.add("     Jude: Eh, just stick to the route and keep your head down. You ever have to walk that stretch behind the garden lately?");
                lines.add(" Patrice: Not a chance. That corner’s been shut off for years — nothing down there but dust and cobwebs.");
                lines.add("     Jude: Don’t blame you, impossible to see a thing in there as well. I don’t fancy getting lost down there again.");
                lines.add(" Patrice: Still, funny how they never properly bricked it up. Just left the old gate hanging open.");
                lines.add("     Jude: Let it rot, I say. No one’s brave enough to go near it anyway.");
                return lines;
            }

            public static ArrayList<String> deliveryConvo() {
                ArrayList<String> lines = new ArrayList<>();
                lines.add(" Scott: ...just saying, I hope whoever takes the van actually remembers how that new scanner works.");
                lines.add("     Dylan: Yeah, no kidding. Half the lads couldn’t get the thing to read their prints this morning.");
                lines.add(" Scott: Mine kept flashing red for no reason. Thought I was locked out for good.");
                lines.add("     Dylan: Which is great, considering someone’s supposed to head off with those leaflets any minute now.");
                lines.add(" Scott: And we still don’t know who that ‘someone’ is. Real organized, this place.");
                lines.add("     Dylan: Well, whoever it ends up being, I hope they get through the gate without wrestling with that stupid scanner again.");
                return lines;
            }

            public static ArrayList<String> lieToGuards() {
                ArrayList<String> lie = new ArrayList<>();
                lie.add(" Player: Hey lads ye are good to go on break early, its dead.");
                lie.add("   Patrice: Hardly.");
                lie.add("       Jude: Yah I swear we had a couple tours in today.");
                lie.add("   Patrice: That's what I thought as well.");
                lie.add(" Player: That's what I was told anyway.");
                lie.add("       Jude: Who told you that?");
                return lie;
            }

            public static ArrayList<String> responseRight() {
                ArrayList<String> right = new ArrayList<>();
                right.add(" Player: Tobias said it.");
                right.add("       Jude: Right fair enough.");
                right.add("   Patrice: Won't argue with that, see you later so.");
                right.add("Guards head into break room.");
                return right;
            }

            public static ArrayList<String> responseWrong() {
                ArrayList<String> wrong = new ArrayList<>();
                wrong.add("       Jude: Who?");
                wrong.add("   Patrice: They're only new don't worry.");
                wrong.add("       Jude: oh yah, it's fine we will wait till we hear from a supervisor.");
                wrong.add(" Player: ehhh.");
                wrong.add("They remain in gallery.");
                return wrong;
            }
    }
    }

