// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "./utils/Ownable.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract IobToken is ERC20, Ownable {

    /**
     * @dev Constructor that gives msg.sender all of existing tokens.
     */
    constructor(string memory name, string memory symbol, uint256 initialSupply) ERC20(name, symbol) {
        _mint(msg.sender, initialSupply);
    }

    /**
     * @dev Creates `amount` tokens and assigns them to `account`.
     * @param account account to put the new tokens in.
     * @param amount amount of new tokens.
     */
    function mint(address account, uint256 amount) public onlyOwner {
        _mint(account, amount);
    }

    /**
     * @dev Destroys `amount` tokens from `account`.
     * @param amount amount of tokens destroyed.
     */
    function burn(address account, uint256 amount) public onlyOwner {
        _burn(account, amount);
    }
}