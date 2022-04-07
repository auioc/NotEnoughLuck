function initializeCoreMod() {
    ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

    Opcodes = Java.type('org.objectweb.asm.Opcodes');

    InsnList = Java.type('org.objectweb.asm.tree.InsnList');

    FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
    MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
    TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');

    return {
        'Player#createAttributes': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.entity.player.Player',
                methodName: ASMAPI.mapMethod('m_36340_'),
                methodDesc:
                    '()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;',
            },
            transformer: function (methodNode) {
                var toInject = new InsnList();
                {
                    toInject.add(
                        new FieldInsnNode(
                            Opcodes.GETSTATIC,
                            'org/auioc/mcmod/notenoughluck/common/attribute/NELAttributes',
                            'UNSEI',
                            'Lnet/minecraftforge/registries/RegistryObject;'
                        )
                    );
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL,
                            'net/minecraftforge/registries/RegistryObject',
                            'get',
                            '()Lnet/minecraftforge/registries/IForgeRegistryEntry;'
                        )
                    );
                    toInject.add(
                        new TypeInsnNode(
                            Opcodes.CHECKCAST,
                            'net/minecraft/world/entity/ai/attributes/Attribute'
                        )
                    );
                    toInject.add(
                        new MethodInsnNode(
                            Opcodes.INVOKEVIRTUAL,
                            'net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder',
                            ASMAPI.mapMethod('m_22266_'),
                            '(Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;'
                        )
                    );
                }

                var instructions = methodNode.instructions;
                instructions.insertBefore(instructions.getLast(), toInject);

                // print(ASMAPI.methodNodeToString(methodNode));
                return methodNode;
            },
        },
    };
}

//! SRG <-> MCP
/*
    m_36340_    createAttributes
    m_22266_    add
*/

//! Original method
/*
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
            .add(Attributes.ATTACK_DAMAGE, 1.0D)
            .add(Attributes.MOVEMENT_SPEED, (double) 0.1F)
            .add(Attributes.ATTACK_SPEED)
            .add(Attributes.LUCK)
            .add(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get())
            .add(Attributes.ATTACK_KNOCKBACK);
    }
*   ========== ByteCode ==========   *
    INVOKESTATIC net/minecraft/world/entity/LivingEntity.createLivingAttributes ()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_DAMAGE : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    DCONST_1
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.MOVEMENT_SPEED : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 0.10000000149011612
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_SPEED : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.LUCK : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraftforge/common/ForgeMod.REACH_DISTANCE : Lnet/minecraftforge/registries/RegistryObject;
    INVOKEVIRTUAL net/minecraftforge/registries/RegistryObject.get ()Lnet/minecraftforge/registries/IForgeRegistryEntry;
    CHECKCAST net/minecraft/world/entity/ai/attributes/Attribute
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_KNOCKBACK : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    ARETURN
*/

//! Transformed method
/*
    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
            .add(Attributes.ATTACK_DAMAGE, 1.0D)
            .add(Attributes.MOVEMENT_SPEED, (double) 0.1F)
            .add(Attributes.ATTACK_SPEED)
            .add(Attributes.LUCK)
            .add(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get())
+           .add(org.auioc.mcmod.notenoughluck.common.attribute.NELAttributes.UNSEI.get())
            ;
    }
*   ========== ByteCode ==========   *
    INVOKESTATIC net/minecraft/world/entity/LivingEntity.createLivingAttributes ()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_DAMAGE : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    DCONST_1
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.MOVEMENT_SPEED : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 0.10000000149011612
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_SPEED : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.LUCK : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraftforge/common/ForgeMod.REACH_DISTANCE : Lnet/minecraftforge/registries/RegistryObject;
    INVOKEVIRTUAL net/minecraftforge/registries/RegistryObject.get ()Lnet/minecraftforge/registries/IForgeRegistryEntry;
    CHECKCAST net/minecraft/world/entity/ai/attributes/Attribute
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_KNOCKBACK : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
+   GETSTATIC org/auioc/mcmod/notenoughluck/common/attribute/NELAttributes.UNSEI : Lnet/minecraftforge/registries/RegistryObject;
+   INVOKEVIRTUAL net/minecraftforge/registries/RegistryObject.get ()Lnet/minecraftforge/registries/IForgeRegistryEntry;
+   CHECKCAST net/minecraft/world/entity/ai/attributes/Attribute
+   INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    ARETURN
*/
