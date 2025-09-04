package br.com.mariojp.solid.dip;

public class EmailNotifier {
	private final MailSender mailSender;

	public EmailNotifier() {
		// Verifica a propriedade DRY_RUN
		if ("true".equalsIgnoreCase(System.getProperty("DRY_RUN"))) {
			// Cria uma classe anônima para a implementação de teste
			this.mailSender = new MailSender() {
				@Override
				public void send(String to, String subject, String body) {
					System.out.println("DRY_RUN: Simulação de envio para " + to);
				}
			};
		} else {
			// Usa o cliente SMTP real
			this.mailSender = new SmtpClient();
		}
	}

	public void welcome(User user) {
		mailSender.send(user.email(), "Bem-vindo", "Olá " + user.name());
	}
}