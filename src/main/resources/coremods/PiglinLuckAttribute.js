function initializeCoreMod() {
    ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI');

    Opcodes = Java.type('org.objectweb.asm.Opcodes');

    InsnList = Java.type('org.objectweb.asm.tree.InsnList');

    FieldInsnNode = Java.type('org.objectweb.asm.tree.FieldInsnNode');
    MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');
    TypeInsnNode = Java.type('org.objectweb.asm.tree.TypeInsnNode');

    return {
        'Piglin#createAttributes': {
            target: {
                type: 'METHOD',
                class: 'net.minecraft.world.entity.monster.piglin.Piglin',
                methodName: ASMAPI.mapMethod('m_34770_'),
                methodDesc:
                    '()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;',
            },
            transformer: function (methodNode) {
                var toInject = new InsnList();
                {
                    toInject.add(
                        new FieldInsnNode(
                            Opcodes.GETSTATIC,
                            'net/minecraft/world/entity/ai/attributes/Attributes',
                            ASMAPI.mapField('f_22286_'),
                            'Lnet/minecraft/world/entity/ai/attributes/Attribute;'
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
    m_34770_    createAttributes
    f_22286_    LUCK
    m_22266_    add
*/

//! Original method
/*
    public static AttributeSupplier.Builder createAttributes() {
         return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 16.0D)
            .add(Attributes.MOVEMENT_SPEED, (double)0.35F)
            .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }
*   ========== ByteCode ==========   *
    INVOKESTATIC net/minecraft/world/entity/monster/Monster.createMonsterAttributes ()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.MAX_HEALTH : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 16.0
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.MOVEMENT_SPEED : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 0.3499999940395355
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_DAMAGE : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 5.0
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    ARETURN
*/

//! Transformed method
/*
    public static AttributeSupplier.Builder createAttributes() {
         return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 16.0D)
            .add(Attributes.MOVEMENT_SPEED, (double)0.35F)
            .add(Attributes.ATTACK_DAMAGE, 5.0D)
+           .add(Attributes.LUCK)
            ;
    }
*   ========== ByteCode ==========   *
    INVOKESTATIC net/minecraft/world/entity/monster/Monster.createMonsterAttributes ()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.MAX_HEALTH : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 16.0
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.MOVEMENT_SPEED : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 0.3499999940395355
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.ATTACK_DAMAGE : Lnet/minecraft/world/entity/ai/attributes/Attribute;
    LDC 5.0
    INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
+   GETSTATIC net/minecraft/world/entity/ai/attributes/Attributes.LUCK : Lnet/minecraft/world/entity/ai/attributes/Attribute;
+   INVOKEVIRTUAL net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add (Lnet/minecraft/world/entity/ai/attributes/Attribute;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;
    ARETURN
*/
