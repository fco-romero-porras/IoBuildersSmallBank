// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "./utils/Pausable.sol";
import "./utils/Ownable.sol";
import "./IobToken.sol";
import "@openzeppelin/contracts/utils/math/SafeMath.sol";

contract IobWallet is Ownable, Pausable {

    using SafeMath for uint256;

    IERC20 private _token;
    
    uint256 private _rate; // How many token units a buyer gets per wei.

    constructor(uint256 rate, IERC20 token) {
        require(rate > 0, "IobWallet: rate is 0");
        require(address(token) != address(0), "IobWallet: token is the zero address");
        
        _rate = rate;
        _token = token;
    }

    /**
     * @dev msg.sender receives tokens based on sent value and token rate.
     */
    function buy() payable public whenNotPaused {
        uint256 amountTobuy = _getTokenAmount(msg.value);
        require(amountTobuy > 0, "You need to send some ether");
        
        uint256 walletBalance = _token.balanceOf(address(this));
        require(amountTobuy <= walletBalance, "Not enough tokens in the reserve");

        _token.transfer(msg.sender, amountTobuy);
    }

    /**
     * @dev Wallet gets back tokens and pays msg.sender for them.
     * @param amount quantity of tokens to be sold.
     */
    function sell(uint256 amount) public whenNotPaused {
        require(amount > 0, "You need to sell at least some tokens");
        uint256 amountToRetreive = _getWeiAmount(amount);
        
        uint256 allowance = _token.allowance(msg.sender, address(this));
        require(allowance >= amount, "Check the token allowance");
         
        _token.transferFrom(msg.sender, address(this), amount);
        
        payable(msg.sender).transfer(amountToRetreive);
    }

    /**
     * @dev Creates `amount` tokens and assigns them this contract
     * @param amount amount of new tokens.
     */
    function mintTokens(uint256 amount) public onlyOwner {
        IobToken(address(_token)).mint(address(this), amount);
    }

    /**
     * @dev Destroys `amount` tokens from this contract.
     * @param amount amount of tokens destroyed.
     */
    function burnTokens(uint256 amount) public onlyOwner {
        IobToken(address(_token)).burn(address(this), amount);
    }

    /**
     * @return The rate value.
     */
    function getRate() public view returns(uint256){
        return _rate;
    }

    /**
     * @return The address of the Token.
     */
    function getTokenAddress() public view returns(address){
        return address(_token);
    }

    /**
     * @dev Wei convertion to tokens.
     * @param weiAmount Value in wei to be converted into tokens
     * @return Number of tokens that can be purchased with the specified weiAmount
     */
    function _getTokenAmount(uint256 weiAmount) internal view returns (uint256) {
        return weiAmount.mul(_rate);
    }

    /**
     * @dev Token convertion to Wei.
     * @param tokenAmount Tokens to be converted into wei
     * @return Amount of wei that represents the tokenAmount
     */
    function _getWeiAmount(uint256 tokenAmount) internal view returns (uint256) {
        return tokenAmount.div(_rate);
    }
}