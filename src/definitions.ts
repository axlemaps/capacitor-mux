export interface CapacitorMuxPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
