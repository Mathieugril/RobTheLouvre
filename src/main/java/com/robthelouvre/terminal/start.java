package com.robthelouvre.terminal;

import java.util.ArrayList;

public class start {
    private static User player;
    private static Room balcony, outside, lobby, regaliaGallery, mastersGallery,
            securityRoom, guardRoom, serviceTunnel, janitorCloset,
            deliveryDock, garden, secretPassage, vip, basementTunnel, van;
   // private static Cameras regCam, deliveryScanner;
    public static Guards patrick, jerry, sean, david, jude;

    public static void create() {
        Item Uniform = new BasicItem("Uniform", Text.ItemDESC.UNIFORM);

        Item FlashLight = new BasicItem("Flashlight", Text.ItemDESC.FLASHLIGHT);
        Item Crown = new BasicItem("Crown", Text.ItemDESC.CROWN);

        Item Headphones = new BasicItem("Headphones", Text.ItemDESC.HEADPHONES);
        Item Waffles = new BasicItem("Waffles", Text.ItemDESC.WAFFLES);

        Item Snus = new BasicItem("Snus", Text.ItemDESC.SNUS);
        Item Monster = new BasicItem("Monster", Text.ItemDESC.MONSTER);

        Item Bread = new BasicItem("Bread", Text.ItemDESC.BREAD);
        Item KeyCard = new BasicItem("Keycard", Text.ItemDESC.KEYCARD);

        Item VanKeys = new BasicItem("Van-Key", Text.ItemDESC.VANKEYS);

        Cameras regCam = new Cameras(regaliaGallery);
        Cameras deliveryScanner = new Cameras();


        ArrayList<Item> regaliaGalleryItems = new ArrayList<Item>();
        ArrayList<Item> securityItems = new ArrayList<Item>();
        ArrayList<Item> guardRoomItems = new ArrayList<Item>();
        ArrayList<Item> dockItems = new ArrayList<Item>();


        outside = new Room(Text.Descriptions.OUTSIDE);
        balcony = new Room(Text.Descriptions.BALCONY1);
        lobby = new Room(Text.Descriptions.LOBBY);
        regaliaGallery = new Room(Text.Descriptions.REGALIA, regaliaGalleryItems, Text.Convos.regaliaConvo());
        mastersGallery = new Room(Text.Descriptions.MASTERS);
        securityRoom = new Room(Text.Descriptions.SECURITY, securityItems);
        guardRoom = new Room(Text.Descriptions.GUARDS, guardRoomItems);
        serviceTunnel = new Room(Text.Descriptions.SERVICE);
        janitorCloset = new Room(Text.Descriptions.JANITOR);
        deliveryDock = new Room(Text.Descriptions.DELIVERY, dockItems, Text.Convos.deliveryConvo());
        garden = new Room(Text.Descriptions.GARDEN);
        secretPassage = new Room(Text.Descriptions.PASSAGE);
        vip = new Room(Text.Descriptions.VIP);
        basementTunnel = new Room(Text.Descriptions.BASEMENT);
        van = new Room(Text.Descriptions.VAN);


        balcony.setExit("north", regaliaGallery, true);
        balcony.setExit("down", garden, true);
        balcony.setDetails(Text.Details.BALCONY_DET1);

        lobby.setExit("west", outside);
        lobby.setExit("east", mastersGallery, true);
        lobby.setExit("south", vip);
        lobby.setDetails(Text.Details.LOBBY_DET);

        regaliaGallery.setExit("north", mastersGallery);
        regaliaGallery.setExit("south", balcony, true);
        regaliaGallery.setExit("east", securityRoom);
        regaliaGallery.setDetails(Text.Details.REGALIA_DET);
        regaliaGalleryItems.add(FlashLight);
        regaliaGalleryItems.add(Crown);

        mastersGallery.setExit("south", regaliaGallery);
        mastersGallery.setExit("west", lobby, true);
        mastersGallery.setDetails(Text.Details.MASTERS_DET);

        securityRoom.setExit("west", regaliaGallery);
        securityRoom.setExit("east", guardRoom);
        securityRoom.setDetails(Text.Details.SECURITY_DET);
        securityItems.add(VanKeys);

        guardRoom.setExit("west", securityRoom);
        guardRoom.setExit("north", janitorCloset, true);
        guardRoom.setDetails(Text.Details.GUARD_DET);
        guardRoomItems.add(Uniform);

        janitorCloset.setExit("south", guardRoom, true);
        janitorCloset.setExit("north", serviceTunnel, true);
        janitorCloset.setDetails(Text.Details.JANITOR_DET);
        janitorCloset.setExit("east", secretPassage);

        serviceTunnel.setExit("north", deliveryDock, true);
        serviceTunnel.setExit("south", janitorCloset, true);
        serviceTunnel.setDetails(Text.Details.SERVICE_DET);

        deliveryDock.setExit("south", serviceTunnel, true);
        deliveryDock.setDetails(Text.Details.DELIVERY_DET);

        basementTunnel.setExit("north", outside, true);
        basementTunnel.setExit("south", deliveryDock, true);
        basementTunnel.setDetails(Text.Details.BASEMENT_DET);

        garden.setExit("up", balcony, true);
        garden.setExit("south", outside);
        garden.setExit("east", secretPassage);
        garden.setDetails(Text.Details.GARDEN_DET);

        secretPassage.setExit("north", janitorCloset);
        secretPassage.setExit("back", garden, true);
        secretPassage.setDetails(Text.Details.PASSAGE_DET1);

        vip.setExit("north", lobby, true);
        vip.setDetails(Text.Details.VIP_DET);

        van.setExit("out", basementTunnel, true);
        van.setExit("north", basementTunnel, true);
        van.setDetails(Text.Details.VAN_DET);


        player = new User("Player", balcony);

        jerry = new Guards("Gerard", guardRoom);
        jerry.getInventory().add(Headphones);
        jerry.getInventory().add(Waffles);

        sean = new Guards("Jean", guardRoom);
        sean.getInventory().add(Snus);
        sean.getInventory().add(Monster);

        david = new Guards("David", guardRoom);
        david.getInventory().add(Bread);
        david.getInventory().add(KeyCard);

        Guards randomGuards = new Guards("Guards", securityRoom);

        patrick = new Guards("Patrice", regaliaGallery);
        jude = new Guards("Jude", regaliaGallery);

        Guards scott = new Guards("Scott", deliveryDock);
        Guards dylan = new Guards("Dylan", deliveryDock);

        Character.addCharacter(player);
        Character.addCharacter(jerry);
        Character.addCharacter(sean);
        Character.addCharacter(david);
        Character.addCharacter(patrick);
        Character.addCharacter(jude);
        Character.addCharacter(scott);
        Character.addCharacter(dylan);
        Character.addCharacter(randomGuards);
    }
}

