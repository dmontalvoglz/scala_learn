import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.util.Random

class Pokemon(var name: String, var level: Int, var tipo: String):
    var hp: Double = tipo match
        case "rock" => Random.between(10, 13) * level
        case "water" => Random.between(12, 16) * level
        case "fire" => Random.between(8, 11) * level
        case "grass" => Random.between(11, 14) * level
        case _ => Random.between(3, 9) * level

    var maxHp: Double = hp

    var attack: Double = tipo match
        case "rock" => Random.between(3, 4) * level
        case "water" => Random.between(3, 5) * level
        case "fire" => Random.between(5, 7) * level
        case "grass" => Random.between(4, 6) * level
        case _ => Random.between(1, 4) * level

    var defense: Double = tipo match
        case "rock" => Random.between(5, 7) * level
        case "water" => Random.between(3, 5) * level
        case "fire" => Random.between(2, 4) * level
        case "grass" => Random.between(3, 5) * level
        case _ => Random.between(1, 3) * level

    var exp = 0


    def normalAttack(p: Pokemon, is_weak: Boolean): Unit =

        var damage: Double = attack * Random.between(0.9, 1.1)
        var battle_phase: Double = damage - p.defense

        if (is_weak == true && damage > 0) then
            println("Your attack is very effective")
            battle_phase = battle_phase * 1.2
        else
            ()

        if battle_phase <= 0 then
            var minor_damage = damage * Random.between(0.1, 0.3)
            p.hp = p.hp - minor_damage
            println(s"Your $name dealt a meager ${BigDecimal(minor_damage).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points because ${p.name}'s defense was superior.")
        else
            p.hp = p.hp - battle_phase
            println(s"Your $name has attacked ${p.name} for ${BigDecimal(battle_phase).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points.")

    def chargedAttack(p: Pokemon, is_weak: Boolean): Unit =

        var damage: Double  = attack * Random.between(1.1, 1.4) * 1.7
        var battle_phase: Double = damage - p.defense

        if battle_phase <= 0 then
            if is_weak == true then
                var minor_damage = damage * Random.between(0.3, 0.5)
                minor_damage = minor_damage * 1.6    
                println(s"Your $name effectively broke ${p.name}'s defense and damaged it for ${BigDecimal(minor_damage).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points!")
                p.hp = p.hp - minor_damage
            else
                var minor_damage = damage * Random.between(0.3, 0.5)
                minor_damage = minor_damage * 1.2    
                println(s"Your $name broke ${p.name}'s defense and damaged it for ${BigDecimal(minor_damage).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points!")
                p.hp = p.hp - minor_damage
        else
            if is_weak == true then
                battle_phase = battle_phase * 1.2
                p.hp = p.hp - battle_phase
                println(s"Your $name has EFFECTIVELY SMASHED ${p.name} for ${BigDecimal(battle_phase).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points!")
            else
                p.hp = p.hp - battle_phase
                println(s"Your $name has SMASHED ${p.name} for ${BigDecimal(battle_phase).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points!")


@main def pokegame = 

    val brooksGeodude = Pokemon("Geodude", Random.between(10, 15), "rock")

    // Start and select your pokemon
    println("""
=========================================================================================
                Hi, Welcome to this mini Pokemon adventure, 
        please choose a pokemon starter from the list using its number:
                        1.- Charmander (Fire Type)
                        2.- Squirtle (Water Type)
                        3.- Bulbasaur (Grass Type)
=========================================================================================
                """)

    var choice = readInt()

    // forces the player to select one of 3 pokemons.
    while (choice != 1 && choice != 2 && choice != 3) do
        println("""
=========================================================================================
Please choose a pokemon from the list:
    1.- Charmander (Fire Type)
    2.- Squirtle (Water Type)
    3.- Bulbasaur (Grass Type)
=========================================================================================
                        """)
        choice = readInt()

    var myPokemon = choice match
        case 1 => Pokemon("Charmander", 5, "fire")
        case 2 => Pokemon("Squirtle", 5, "water")
        case _ => Pokemon("Bulbasaur", 5, "grass")

    var rivalPokemon = choice match
        case 1 => Pokemon("Squirtle", 30, "water")
        case 2 => Pokemon("Bulbasaur", 30, "grass")
        case _ => Pokemon("Charmander", 30, "fire")

    println(s"""
=========================================================================================
            Excelent! you now have a ${myPokemon.name}, level ${myPokemon.level}!!
and now you're about to arrive to Pewter City where you can try to defeat the gym leader,
                                ready? (Press 'Enter')
=========================================================================================
                    """)
    readLine()

    def farmRattatas(myPokemon: Pokemon): Unit =

        var wildRattata = Pokemon("Rattata", Random.between(3, 8), "normal")

        if myPokemon.level < 12 then
            wildRattata = Pokemon("Rattata", Random.between(3, 8), "normal")
        else
            wildRattata = Pokemon("Rattata", Random.between(8, 12), "normal")

        val is_rattata_weak = false

        def rattata_Attack() =
            var rattatas_damage: Double = wildRattata.attack * Random.between(0.9, 1.3)
            var rattatas_battle_phase: Double = rattatas_damage - myPokemon.defense

            if rattatas_battle_phase <= 0 then
                var rattatas_minor_damage = rattatas_damage * Random.between(0.1, 0.3)
                myPokemon.hp = myPokemon.hp - rattatas_minor_damage
                println(s"Rattata dealt a meager ${BigDecimal(rattatas_minor_damage).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points because ${myPokemon.name}'s defense was superior.")
            else
                myPokemon.hp = myPokemon.hp - rattatas_battle_phase
                println(s"${wildRattata.name} has attacked ${myPokemon.name} for ${BigDecimal(rattatas_battle_phase).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points.")

        while wildRattata.hp > 0 && myPokemon.hp > 0 do
            println(s"""
=========================================================================================                                                           
                                   --===-::                 
                                  ---==++===-.              
                                  .=-===++*===-.            
                                   .=+=++++:-===-           
                                      ::::    :===          
                -+==:                           ===         
               =+++++=                           ==-        
              :+==++++....     .:----==           ++        
              -*===-----===-::=======+=:          -+-       
        .-:.  :=-------========+=====+==          .*-       
          .-===-==========+===++----=++-   ...    .+=       
         :-===========+*-.-+++++::::=*+-:-----==-.-+-       
         :+=======++*+=*  -+++++==-=+++===========++        
          -=-----:::...::-===========+===++=========        
         .-.....=+=::::::::-=++====================+        
   ...:===+ .  +*+++=::::::::=======================:       
   .:-:--=+==.=++*++===:::::::++==========---=======+.      
   ::::::::::::---+----=:::::========+=-:....-=====+++:     
                  +:::--:-::+=======+---------=+==++++++=   
                  -=---:.  ========*=++++::.    :==++++++.  
                          =+=====+*-==::           :+=++*   
                         .-=++++=.               .--.-.-:   
                       --...:+-.                  .-.-.     
                       ::.=.. 

${wildRattata.name}'s lvl: ${wildRattata.level}    
${wildRattata.name}'s HP: ${BigDecimal(wildRattata.hp).setScale(2, BigDecimal.RoundingMode.HALF_UP)}

Your ${myPokemon.name}'s lvl: ${myPokemon.level}
Your ${myPokemon.name}'s HP: ${BigDecimal(myPokemon.hp).setScale(2, BigDecimal.RoundingMode.HALF_UP)}

Actions:
1.- Normal attack
2.- Charged attack (Powerful move but needs a turn to recharge.)
=========================================================================================""")

            var action = readInt()
            while (action != 1 && action != 2) do 
                println("""
=========================================================================================
Select a valid option!
=========================================================================================
                """)
                action = readInt()

            action match
                case 1 => 
                    println("++++++++++")
                    myPokemon.normalAttack(wildRattata, is_rattata_weak)
                    println("++++++++++")
                    rattata_Attack()
                    println("++++++++++")
                case 2 => 
                    println("++++++++++")
                    myPokemon.chargedAttack(wildRattata, is_rattata_weak)
                    println("++++++++++")
                    rattata_Attack()
                    println("++++++++++")
                    println(s"Your ${myPokemon.name} must recharge after that strenuous move.")
                    println("++++++++++")
                    rattata_Attack()
                    println("++++++++++")

        if wildRattata.hp <= 0 then
            myPokemon.exp = myPokemon.exp + wildRattata.level * 10
            println(s"""
=========================================================================================
You defeated a wild Rattata and gained ${wildRattata.level * 10} Exp points.
=========================================================================================""")
            pewterCity(myPokemon)
        else if myPokemon.hp <= 0 then
            println("=========================================================================================")
            println(s"Your ${myPokemon.name} has fainted. GAME OVER")
            println("=========================================================================================")
        else
            ()

    def fightBrook(myPokemon: Pokemon, geodude: Pokemon): Unit =
        val is_geodude_weak = myPokemon.tipo match
            case "water" => true
            case "grass" => true
            case _ => false

        val is_myPokemon_weak = myPokemon.tipo match
            case "fire" => true
            case _ => false

        def geodude_Attack() =
            var geodudes_damage: Double = geodude.attack * Random.between(0.9, 1.3)
            var geodudes_battle_phase: Double = geodudes_damage - myPokemon.defense

            if (is_myPokemon_weak == true && geodudes_damage > 0) then
                println("Geodude's attack was very effective")
                geodudes_battle_phase = geodudes_battle_phase * 1.2
            else
                ()

            if geodudes_battle_phase <= 0 then
                var geodudes_minor_damage = geodudes_damage * Random.between(0.1, 0.3)
                myPokemon.hp = myPokemon.hp - geodudes_minor_damage
                println(s"Geodude dealt a meager ${BigDecimal(geodudes_minor_damage).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points because ${myPokemon.name}'s defense was superior.")
            else
                myPokemon.hp = myPokemon.hp - geodudes_battle_phase
                println(s"${geodude.name} has attacked ${myPokemon.name} for ${BigDecimal(geodudes_battle_phase).setScale(2, BigDecimal.RoundingMode.HALF_UP)} Hit Points.")
         

        while geodude.hp > 0 && myPokemon.hp > 0 do
            println(s"""
=========================================================================================
                                                   'ldc.    
   .';::;.                 .....                 'cdxxxo;.  
.;ldddoll;           .',;:ldddddc.              .cdxxxkkxo' 
oxdolccccl;.      .,:cldxkxxdoddo:'.           .:ccldxxxxxd,
oxolcccccc;.     'oxxddxxxxdoc:ccccc;.         .:c::lodxxxxo
'oxxxlc:'       'oxxxdxxdxxkoloolcccc:;:;'..;cllllcccclodxo:
 .cxxdlc'      .cxOklcdxdx0KOk0xlccclodddoodxxddoool:,..... 
   :xxxxdc.     .oKKdlxkxxxdddolccccc;'..',;cllcc:,..       
   .cxkkkd,  .',;okkxxxddddolccccccc:.                      
    .cxxxxc.'ldl;:ldxxxxxxxocccccc:,.                       
     .';cdxdol,.   .;oxxdolcccc:;'.                         
        .'cl:.       .';;::::,'.

Enemy's ${geodude.name} lvl: ${geodude.level}    
Enemy's ${geodude.name} HP: ${BigDecimal(geodude.hp).setScale(2, BigDecimal.RoundingMode.HALF_UP)}

Your ${myPokemon.name}'s lvl: ${myPokemon.level}
Your ${myPokemon.name}'s HP: ${BigDecimal(myPokemon.hp).setScale(2, BigDecimal.RoundingMode.HALF_UP)}

Actions:
1.- Normal attack
2.- Charged attack (Powerful move but needs a turn to recharge.)
=========================================================================================""")

            var action = readInt()
            while (action != 1 && action != 2) do 
                println("""
=========================================================================================
Select a valid option!
=========================================================================================
                """)
                action = readInt()

            action match
                case 1 => 
                    println("++++++++++")
                    myPokemon.normalAttack(geodude, is_geodude_weak)
                    println("++++++++++")
                    geodude_Attack()
                    println("++++++++++")
                case 2 => 
                    println("++++++++++")
                    myPokemon.chargedAttack(geodude, is_geodude_weak)
                    println("++++++++++")
                    geodude_Attack()
                    println("++++++++++")
                    println(s"Your ${myPokemon.name} must recharge after that strenuous move.")
                    println("++++++++++")
                    geodude_Attack()
                    println("++++++++++")

        if geodude.hp <= 0 then
            myPokemon.exp = myPokemon.exp + geodude.level * 12
            println(s"""
=========================================================================================
You defeated Brook's Geodude and gained ${geodude.level * 12} Exp points.
=========================================================================================""")
            pewterCity(myPokemon)
        else if myPokemon.hp <= 0 then
            println("=========================================================================================")
            println(s"Your ${myPokemon.name} has fainted. GAME OVER")
            println("=========================================================================================")
        elsea
    def pewterCity(myPokemon: Pokemon) =

        if myPokemon.exp >= 100 then
            var storedExp = myPokemon.exp / 100
            var residualExp = myPokemon.exp % 100
            var previousLevel = myPokemon.level

            myPokemon.level = myPokemon.level + storedExp
            myPokemon.exp = storedExp

            myPokemon.maxHp = myPokemon.tipo match
                case "rock" => myPokemon.maxHp / previousLevel * myPokemon.level
                case "water" => myPokemon.maxHp / previousLevel * myPokemon.level
                case "fire" => myPokemon.maxHp / previousLevel * myPokemon.level
                case "grass" => myPokemon.maxHp / previousLevel * myPokemon.level
                case _ => myPokemon.maxHp / previousLevel * myPokemon.level

            myPokemon.hp = myPokemon.maxHp

            myPokemon.attack = myPokemon.tipo match
                case "rock" => myPokemon.attack / previousLevel * myPokemon.level
                case "water" => myPokemon.attack / previousLevel * myPokemon.level
                case "fire" => myPokemon.attack / previousLevel * myPokemon.level
                case "grass" => myPokemon.attack / previousLevel * myPokemon.level
                case _ => myPokemon.attack / previousLevel * myPokemon.level

            myPokemon.defense = myPokemon.tipo match
                case "rock" => myPokemon.defense / previousLevel * myPokemon.level
                case "water" => myPokemon.defense / previousLevel * myPokemon.level
                case "fire" => myPokemon.defense / previousLevel * myPokemon.level
                case "grass" => myPokemon.defense / previousLevel * myPokemon.level
                case _ => myPokemon.defense / previousLevel * myPokemon.level

            println(s"""
=========================================================================================
Your ${myPokemon.name} advanced to level ${myPokemon.level}!
New Stats:
    HP: ${myPokemon.maxHp}
    atk: ${myPokemon.attack}
    def: ${myPokemon.defense}

${100 - residualExp} more exp points to level up again!
=========================================================================================""")
        else
            ()

        println(s"""
=========================================================================================
You are in Pewter City.
Your Pokemon: ${myPokemon.name}
Your Pokemon's HP ${BigDecimal(myPokemon.hp).setScale(2, BigDecimal.RoundingMode.HALF_UP)}
Pokemon's lvl: ${myPokemon.level}
Pokemon's exp: ${myPokemon.exp}

What do you want to do? (choose an option)
1.- Fight gym leader Brook
2.- Farm nearby Rattatas
3.- Quit
=========================================================================================
""")
        var action = readInt()
        while (action != 1 && action != 2 && action != 3) do 
            println("""
=========================================================================================
Select a valid option!
=========================================================================================
            """)
            action = readInt()

        action match
        case 1 => fightBrook(myPokemon, brooksGeodude)
        case 2 => farmRattatas(myPokemon)
        case 3 => ()
            
    
    pewterCity(myPokemon)

    