function initializeCoreMod() {
    ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

    Opcodes = Java.type('org.objectweb.asm.Opcodes');

    InsnList = Java.type('org.objectweb.asm.tree.InsnList');

    VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
    FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
    MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
    InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');

    return {
        'PiglinAi#getBarterResponseItems': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.entity.monster.piglin.PiglinAi',
                methodName: 'getBarterResponseItems',
                methodDesc:
                    '(Lnet/minecraft/world/entity/monster/piglin/Piglin;)Ljava/util/List;',
            },
            transformer: function (methodNode) {
                var toInject = new InsnList();
                {
                    toInject.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    toInject.add(
                        new FieldInsnNode(
                            Opcodes.GETSTATIC,
                            'net/minecraft/world/entity/ai/attributes/Attributes',
                            'LUCK',
                            'Lnet/minecraft/world/entity/ai/attributes/Attribute;'
                        )
                    );
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL,
                            'net/minecraft/world/entity/monster/piglin/Piglin',
                            'getAttributeValue',
                            '(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D'
                        )
                    );
                    toInject.add(new InsnNode(Opcodes.D2F));
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL,
                            'net/minecraft/world/level/storage/loot/LootContext$Builder',
                            'withLuck',
                            '(F)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;'
                        )
                    );
                }

                var at = ASMAPI.findFirstMethodCall(
                    methodNode,
                    ASMAPI.MethodType.VIRTUAL,
                    'net/minecraft/world/level/storage/loot/LootContext$Builder',
                    'withRandom',
                    '(Ljava/util/Random;)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;'
                );
                methodNode.instructions.insert(at, toInject);

                // print(ASMAPI.methodNodeToString(methodNode));
                return methodNode;
            },
        },
    };
}

//! LocalVariableTable
/*
    Slot  Name       Signature
    0     p_34997_   Lnet/minecraft/world/entity/monster/piglin/Piglin;
    1     loottable  Lnet/minecraft/world/level/storage/loot/LootTable;
*/

//! Original method
/*
    private static List<ItemStack> getBarterResponseItems(Piglin p_34997_) {
        LootTable loottable = p_34997_.level.getServer().getLootTables().get(BuiltInLootTables.PIGLIN_BARTERING);
        return loottable.getRandomItems(
            (new LootContext.Builder((ServerLevel)p_34997_.level))
            .withParameter(LootContextParams.THIS_ENTITY, p_34997_)
            .withRandom(p_34997_.level.random)
            .create(LootContextParamSets.PIGLIN_BARTER)
        );
    }
*   ========== ByteCode ==========   *
    ALOAD 0
    GETFIELD net/minecraft/world/entity/monster/piglin/Piglin.level : Lnet/minecraft/world/level/Level;
    INVOKEVIRTUAL net/minecraft/world/level/Level.getServer ()Lnet/minecraft/server/MinecraftServer;
    INVOKEVIRTUAL net/minecraft/server/MinecraftServer.getLootTables ()Lnet/minecraft/world/level/storage/loot/LootTables;
    GETSTATIC net/minecraft/world/level/storage/loot/BuiltInLootTables.PIGLIN_BARTERING : Lnet/minecraft/resources/ResourceLocation;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootTables.get (Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/world/level/storage/loot/LootTable;
    ASTORE 1
    ALOAD 1
    NEW net/minecraft/world/level/storage/loot/LootContext$Builder
    DUP
    ALOAD 0
    GETFIELD net/minecraft/world/entity/monster/piglin/Piglin.level : Lnet/minecraft/world/level/Level;
    CHECKCAST net/minecraft/server/level/ServerLevel
    INVOKESPECIAL net/minecraft/world/level/storage/loot/LootContext$Builder.<init> (Lnet/minecraft/server/level/ServerLevel;)V
    GETSTATIC net/minecraft/world/level/storage/loot/parameters/LootContextParams.THIS_ENTITY : Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;
    ALOAD 0
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootContext$Builder.withParameter (Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;Ljava/lang/Object;)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;
    ALOAD 0
    GETFIELD net/minecraft/world/entity/monster/piglin/Piglin.level : Lnet/minecraft/world/level/Level;
    GETFIELD net/minecraft/world/level/Level.random : Ljava/util/Random;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootContext$Builder.withRandom (Ljava/util/Random;)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;
    GETSTATIC net/minecraft/world/level/storage/loot/parameters/LootContextParamSets.PIGLIN_BARTER : Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootContext$Builder.create (Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;)Lnet/minecraft/world/level/storage/loot/LootContext;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootTable.getRandomItems (Lnet/minecraft/world/level/storage/loot/LootContext;)Ljava/util/List;
    ARETURN
*/

//! Transformed method
/*
    private static List<ItemStack> getBarterResponseItems(Piglin p_34997_) {
        LootTable loottable = p_34997_.level.getServer().getLootTables().get(BuiltInLootTables.PIGLIN_BARTERING);
        return loottable.getRandomItems(
            (new LootContext.Builder((ServerLevel)p_34997_.level))
            .withParameter(LootContextParams.THIS_ENTITY, p_34997_)
            .withRandom(p_34997_.level.random)
+           .withLuck((float) p_34997_.getAttributeValue(Attributes.LUCK))
            .create(LootContextParamSets.PIGLIN_BARTER)
        );
    }
*   ========== ByteCode ==========   *
    ALOAD 0
    GETFIELD net/minecraft/world/entity/monster/piglin/Piglin.level : Lnet/minecraft/world/level/Level;
    INVOKEVIRTUAL net/minecraft/world/level/Level.getServer ()Lnet/minecraft/server/MinecraftServer;
    INVOKEVIRTUAL net/minecraft/server/MinecraftServer.getLootTables ()Lnet/minecraft/world/level/storage/loot/LootTables;
    GETSTATIC net/minecraft/world/level/storage/loot/BuiltInLootTables.PIGLIN_BARTERING : Lnet/minecraft/resources/ResourceLocation;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootTables.get (Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/world/level/storage/loot/LootTable;
    ASTORE 1
    ALOAD 1
    NEW net/minecraft/world/level/storage/loot/LootContext$Builder
    DUP
    ALOAD 0
    GETFIELD net/minecraft/world/entity/monster/piglin/Piglin.level : Lnet/minecraft/world/level/Level;
    CHECKCAST net/minecraft/server/level/ServerLevel
    INVOKESPECIAL net/minecraft/world/level/storage/loot/LootContext$Builder.<init> (Lnet/minecraft/server/level/ServerLevel;)V
    GETSTATIC net/minecraft/world/level/storage/loot/parameters/LootContextParams.THIS_ENTITY : Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;
    ALOAD 0
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootContext$Builder.withParameter (Lnet/minecraft/world/level/storage/loot/parameters/LootContextParam;Ljava/lang/Object;)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;
    ALOAD 0
    GETFIELD net/minecraft/world/entity/monster/piglin/Piglin.level : Lnet/minecraft/world/level/Level;
    GETFIELD net/minecraft/world/level/Level.random : Ljava/util/Random;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootContext$Builder.withRandom (Ljava/util/Random;)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;
+   ALOAD 0
+   GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.LUCK : Lnet/minecraft/world/entity/ai/attributes/Attribute;
+   INVOKEVIRTUAL net/minecraft/world/entity/monster/piglin/Piglin.getAttributeValue (Lnet/minecraft/world/entity/ai/attributes/Attribute;)D
+   D2F
+   INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootContext$Builder.withLuck (F)Lnet/minecraft/world/level/storage/loot/LootContext$Builder;
    GETSTATIC net/minecraft/world/level/storage/loot/parameters/LootContextParamSets.PIGLIN_BARTER : Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootContext$Builder.create (Lnet/minecraft/world/level/storage/loot/parameters/LootContextParamSet;)Lnet/minecraft/world/level/storage/loot/LootContext;
    INVOKEVIRTUAL net/minecraft/world/level/storage/loot/LootTable.getRandomItems (Lnet/minecraft/world/level/storage/loot/LootContext;)Ljava/util/List;
    ARETURN
*/
