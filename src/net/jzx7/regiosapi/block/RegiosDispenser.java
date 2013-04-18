package net.jzx7.regiosapi.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jzx7.jnbt.CompoundTag;
import net.jzx7.jnbt.ListTag;
import net.jzx7.jnbt.NBTUtils;
import net.jzx7.jnbt.StringTag;
import net.jzx7.jnbt.Tag;
import net.jzx7.regiosapi.exceptions.InvalidNBTData;
import net.jzx7.regiosapi.inventory.RegiosItemStack;

public class RegiosDispenser extends RegiosContainer {
	
	public RegiosDispenser(int id) {
		super(id, 9);
	}
	
	public RegiosDispenser(int id, byte data) {
		super(id, data, 9);
	}
	
	public RegiosDispenser(int id, byte data, RegiosItemStack[] items) {
        super(id, data, 9);
        setContents(items);
    }

	@Override
	public String getNBTID() {
		return "Trap";
	}

	@Override
	public CompoundTag getNBTData() {
		Map<String, Tag> values = new HashMap<String, Tag>();
        values.put("Items", new ListTag("Items", CompoundTag.class,
                serialize(getContents())));
        return new CompoundTag(getNBTID(), values);
	}

	@Override
	public void setNBTData(CompoundTag nbtData) throws InvalidNBTData {
		if (nbtData == null) {
            return;
        }
        
        Map<String, Tag> values = nbtData.getValue();

        Tag t = values.get("id");
        if (!(t instanceof StringTag) || !((StringTag) t).getValue().equals("Trap")) {
            throw new InvalidNBTData("'Trap' tile entity expected");
        }

        List<CompoundTag> items = new ArrayList<CompoundTag>();
        for (Tag tag : NBTUtils.getChildTag(values, "Items", ListTag.class).getValue()) {
            if (!(tag instanceof CompoundTag)) {
                throw new InvalidNBTData("CompoundTag expected as child tag of Trap Items");
            }

            items.add((CompoundTag) tag);
        }

        setContents(deserialize(items, 9));
	}
}