import { useState } from "react";
import VaultItem from "./VaultItem";

const VaultList = ({ vaultList }) => {
  return (
    <div className="VaultList">
      {vaultList.map((it) => (
        <VaultItem key={it.idx} {...it} />
      ))}
    </div>
  );
};

export default VaultList;
